package com.jvn.degreespree.models;

import android.util.Log;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by vishal on 10/10/15.
 */
public class ComputerPlayer extends Player {
    private static final String TAG = "ComputerPlayer";

    public ComputerPlayer(String name) {
        cards = new ArrayList<>();
        playerName = name;
        r = new Random();
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void startTurn() {
        gameController.drawCard();
        movesLeft = 3;

        Set<BoardPosition> positions = getMovablePositions();

        Card playthis = cardInHand;
        BoardPosition moveHere = boardPosition;

        for (BoardPosition position : positions) {
            boardPosition = position;

            for (Card card : cards) {
                if (card.check(this)) {
                    playthis = card;
                    moveHere = position;
                }
            }
        }

        gameController.movePlayer(moveHere);

        cardInHand = playthis;

        playCardInHand();
    }

    private BoardPosition getRandomPosition() {
        int index = boardPosition.getNearbyPositions().get(r.nextInt(boardPosition.getNearbyPositions().size()));
        return gameController.getGameBoard().getPosition(index);
    }

    private HashSet<BoardPosition> getMovablePositions() {
        HashSet<BoardPosition> positions = new HashSet<>();

        ArrayList<Integer> nearby = boardPosition.getNearbyPositions();

        positions.addAll(gameController.getGameBoard().getPositions(nearby));


        HashSet<BoardPosition> temp = new HashSet<>(positions);
        for (BoardPosition position : temp) {
            nearby = position.getNearbyPositions();
            positions.addAll(gameController.getGameBoard().getPositions(nearby));
        }


        temp = new HashSet<>(positions);
        for (BoardPosition position : temp) {
            nearby = position.getNearbyPositions();
            positions.addAll(gameController.getGameBoard().getPositions(nearby));
        }

        return positions;

    }

    @Override
    public void endTurn() {
        TurnInfo info = new TurnInfo(playerName, boardPosition.toString(), turnCard.toString(), turnReward);
        gameController.addTurnInfo(info);
        gameController.nextTurn();
    }

    public void chooseDiscard() {
        if (cards.size() > 0) {
            Card discard = cards.get(r.nextInt(cards.size()));
            discard(discard);
        }
    }

    public void pickReward(int points, boolean learningOn, boolean craftOn, boolean integrityOn, Reward reward) {
        int remaining = points;

        while (remaining > 0) {
            int pick = r.nextInt(3);

            if (pick == 0 && learningOn) reward.add(1,0,0,0);
            if (pick == 1 && craftOn) reward.add(0,1,0,0);
            if (pick == 2 && integrityOn) reward.add(0,0,1,0);
            remaining--;
        }
    }
}
