package com.jvn.degreespree.models;

/**
 * Created by vishal on 11/7/15.
 */
public class TurnInfo {
    private String playerName;
    private String cardName;
    private String playerPosition;
    private Reward reward;

    public TurnInfo(String playerName, String playerPosition, String cardName, Reward reward) {
        this.playerPosition = playerPosition;
        this.playerName = playerName;
        this.cardName = cardName;
        this.reward = reward;
    }

    @Override
    public String toString() {
        if (reward.wasSuccess()) {
            return playerName + " played " + cardName + " in " + playerPosition +" for " + reward + ".";
        } else {
            return playerName + " played " + cardName + " in " + playerPosition + " but failed to meet requirements.";
        }
    }
}
