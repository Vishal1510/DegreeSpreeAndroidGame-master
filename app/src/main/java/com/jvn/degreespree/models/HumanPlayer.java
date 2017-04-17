package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;

/**
 * Created by vishal on 10/3/15.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        cards = new ArrayList<Card>();
        playerName = name;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public void startTurn() {
        movesLeft = 3;
    }

    @Override
    public void endTurn() {
        TurnInfo info = new TurnInfo(playerName, boardPosition.toString(), turnCard.toString(), turnReward);
        gameController.addTurnInfo(info);
        gameController.nextTurn();
    }
}
