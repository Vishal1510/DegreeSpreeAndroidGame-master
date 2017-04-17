package com.jvn.degreespree.controllers;

import android.content.Context;
import android.util.Log;

import com.jvn.degreespree.MainView;
import com.jvn.degreespree.Utils.ScreenUtils;
import com.jvn.degreespree.fragments.view.BoardViewFragment;
import com.jvn.degreespree.fragments.view.GameplayViewFragment;
import com.jvn.degreespree.fragments.view.StartFragment;
import com.jvn.degreespree.fragments.view.dialogs.DiscardDialog;
import com.jvn.degreespree.fragments.view.dialogs.RewardDialog;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Deck;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;
import com.jvn.degreespree.models.TurnInfo;
import com.jvn.degreespree.models.Year;
import com.jvn.degreespree.models.cards.Card;
import com.jvn.degreespree.models.GameBoard;
import com.jvn.degreespree.models.GameSettings;
import com.jvn.degreespree.models.Player;

import java.util.ArrayList;

/**
 * Created by vishal on 10/3/15.
 */
public class GameController {
    private static final String TAG = "GameController";

    private MainView mainView;
    private Context applicationContext;
    private StartFragment startView;
    private BoardViewFragment boardView;
    private GameplayViewFragment menuView;

    private GameSettings gameSettings;
    private ArrayList<Player> players;
    private Player currentPlayersTurn;
    private int currentPlayerIndex = 0;
    private GameBoard gameBoard;
    private Deck deck;
    private DiscardDialog discardDialog;
    private RewardDialog rewardDialog;
    private Year currentYear;  // its the current year man

    public GameController(MainView mainView) {
        this.mainView = mainView;
        this.applicationContext = mainView.getApplicationContext();

        ScreenUtils.setConversionRate(mainView.getDensity());

        startView = new StartFragment();
        startView.bind(this);

        boardView = new BoardViewFragment();
        boardView.bind(this);
        boardView.init();

        menuView = new GameplayViewFragment();
        menuView.bind(this);
        menuView.init();

        discardDialog = new DiscardDialog();
        discardDialog.setCancelable(false);
        discardDialog.bind(this);

        rewardDialog = new RewardDialog();
        rewardDialog.setCancelable(false);
        rewardDialog.bind(this);

        init();
        startGame();

    }

    private void init() {
        gameBoard = new GameBoard();
        deck = new Deck(this);
    }

    public void startGame() {

        // Switch to Start view eventually
        currentYear = Year.Freshman;

        gameSettings = new GameSettings();
        players = gameSettings.getPlayers();
        menuView.disableUi();

        for (Player player : players) {
            player.setBoardPosition(gameBoard.getPosition(17));
            player.bind(this);
            ArrayList<Card> crds = deck.take(5);
            player.addToHand(crds);
            if (player.isHuman()) {
                menuView.updateCardDisplay(player.getCardInHand());
            }
        }

        viewGameMenu();

        addPlayers(players);

        updateScores();

        startTurn(players.get(currentPlayerIndex));

    }

    private void addPlayers(ArrayList<Player> players) {
        boardView.addPlayers(players);
    }

    private void startTurn(Player player) {
        currentPlayersTurn = player;
        updatePlayerInfo();

        if (player.isHuman()) {
            Log.i(TAG, "Starting turn player.");
            menuView.enableDrawCard();
            menuView.disableMove();
            menuView.disablePlayCard();
            ArrayList<BoardPosition> positions = gameBoard.getPositions(player.getBoardPosition().getNearbyPositions());
            menuView.updateMovableLocation(positions);
            player.startTurn();
        } else if (!player.isHuman()){
            menuView.disableUi();
            Log.i(TAG, "Starting turn computer.");
            player.startTurn();
        }
    }

    public void nextTurn() {
        updateScores();
        updateYear();
        if (!gameHasEnded()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % 3;
            Player nextPlayer = players.get(currentPlayerIndex);

            startTurn(nextPlayer);
        } else {
            endGame();
        }

    }

    public void viewBoard() {
        mainView.setView(boardView);
    }

    public void viewGameMenu() {
        mainView.setView(menuView);
    }

    public void movePlayer(BoardPosition position) {
        if (currentPlayersTurn.getMovesLeft() > 0) {
            currentPlayersTurn.setBoardPosition(position);
            boardView.movePlayer(currentPlayersTurn);
            currentPlayersTurn.decrementMovesLeft();

            if (currentPlayersTurn.isHuman()) {
                ArrayList<BoardPosition> movableLocations = gameBoard.getPositions(position.getNearbyPositions());
                menuView.updateMovableLocation(movableLocations);
            }

            if(currentPlayersTurn.getMovesLeft() == 0 && currentPlayersTurn.isHuman()) menuView.disableMove();

        }
        updatePlayerInfo();
    }

    public void playCard(Card card) {
        currentPlayersTurn.playCardInHand();
        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
        updateScores();
    }

    public void drawCard() {
        Card card = deck.drawCard();
        currentPlayersTurn.addCardToHand(card);

        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
            menuView.disableDrawCard();
            menuView.enableMove();
            menuView.enablePlayCard();
        }

        updateScores();
    }

    public void nextCard() {
        currentPlayersTurn.nextCard();

        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
    }

    public void placeInDiscardPile(Card card) {
        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
        deck.discard(card);
        updateScores();

    }

    public void addTurnInfo(TurnInfo info) {
        menuView.addTurnInfo(info);
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    private boolean gameHasEnded() {
        // check for win conditions
        return false;
    }

    private void endGame() {

    }

    private void updateYear() {
        int totalQP = 0;
        for (Player player : players) {
            totalQP += player.getQualityPoints();
        }

        if (currentYear == Year.Freshman && totalQP >= 60) {
            currentYear = Year.Sophomore;
            for (Player player : players) {
                player.discardAll();
            }
            deck.setYear(Year.Sophomore);

            for (Player player : players) {
                player.addToHand(deck.take(5));
            }

        }
    }

    public void updateScores() {
        menuView.updateScoreBoard(players, deck, currentYear);

    }

    public void updatePlayerInfo() {
        menuView.updatePlayerInfo(currentPlayersTurn);
    }

    public void openRewardDialog(int points, boolean learning, boolean craft, boolean integrity, RewardCallback callback, Reward reward) {
        rewardDialog.setup(points, learning, craft, integrity, callback, reward);
        mainView.showDiag(rewardDialog);
    }

    public void openDiscardDialog(Player player, DiscardCallback callback) {
        if (player.hasCards()) {
            discardDialog.setPlayer(player, callback);
            mainView.showDiag(discardDialog);
        } else {
            callback.discardCallback();
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

}
