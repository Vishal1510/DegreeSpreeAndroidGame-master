package com.jvn.degreespree.models;

import android.util.Log;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vishal on 10/5/15.
 */
abstract public class Player {
    private String TAG = "Player";

    protected String playerName;
    protected BoardPosition boardPosition;

    protected Random r;

    protected int learning = 0;
    protected int craft = 0;
    protected int integrity = 0;
    protected int qualityPoints = 0;

    protected ArrayList<Card> cards = new ArrayList<>();
    protected Card cardInHand;
    protected Card turnCard;
    protected Reward turnReward;
    protected GameController gameController;

    protected int movesLeft = 0;

    public Player() {
        r = new Random();
        initializePoints();
    }

    protected void initializePoints() {
        for (int i = 0; i < 12; i++) {
            int rand = r.nextInt(3);

            if (rand == 0) learning++;
            if (rand == 1) craft++;
            if (rand == 2) integrity++;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(BoardPosition position) {
        boardPosition = position;
    }

    public abstract boolean isHuman();

    public abstract void startTurn();

    public abstract void endTurn();

    public int getLearning() {
        return learning;
    }

    public void setLearning(int learning) {
        this.learning = learning;
    }

    public int getCraft() {
        return craft;
    }

    public void setCraft(int craft) {
        this.craft = craft;
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrety) {
        this.integrity = integrety;
    }

    public int getQualityPoints() {
        return qualityPoints;
    }

    public void setQualityPoints(int qualityPoints) {
        this.qualityPoints = qualityPoints;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void decrementMovesLeft() {
        movesLeft--;
    }

    public void bind(GameController controller) {
        this.gameController = controller;
    }

    public void playCardInHand() {
        Log.d("Player", "Played: " + cardInHand.toString());
        turnCard = cardInHand;
        gameController.placeInDiscardPile(cardInHand);
        cards.remove(cardInHand);
        cardInHand.play(this);


        if (cards.size() > 0) {
            cardInHand = cards.get(0);
        } else {
            cardInHand = null;
        }
    }

    public void nextCard() {
        int index = cards.indexOf(cardInHand);

        index = (index + 1)%cards.size();

        cardInHand = cards.get(index);
    }

    public void addCardToHand(Card card) {
        Log.i(TAG, "Added card: " + card.getImageRef());
        cards.add(0, card);
        cardInHand = cards.get(0);
    }

    public void addToHand(ArrayList<Card> crds) {
        cards.addAll(crds);
        cardInHand = cards.get(0);
    }

    public Card getCardInHand() {
        return cardInHand;
    }

    public Card discardCardInHand() {
        Card card = cardInHand;
        turnCard = card;
        cards.remove(cardInHand);
        gameController.placeInDiscardPile(cardInHand);
        if (cards.size() > 0) {
            cardInHand = cards.get(0);
        } else {
            cardInHand = null;
        }

        return card;

    }

    public void discard(Card card) {
        cards.remove(card);
        if (cards.size() > 0) {
            cardInHand = cards.get(0);
        } else {
            cardInHand = null;
        }
        gameController.placeInDiscardPile(card);

    }

    public void discardAll() {
        for (Card card : cards) {
            gameController.placeInDiscardPile(card);
        }
        cards.clear();
    }

    public void rewardPlayer(Reward reward) {
        turnReward = reward;
        if (!reward.isCorrectPosition()) {
            qualityPoints -= 2;
        }
        if (!reward.isMetPrereqs()) {
            qualityPoints -= 2;
        }
        learning += reward.getLearning();
        integrity += reward.getIntegrity();
        craft += reward.getCraft();
        qualityPoints += reward.getQualityPoints();

        if (qualityPoints < 0)
            qualityPoints = 0;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean hasCards() {
        return (cards.size() > 0);
    }

}
