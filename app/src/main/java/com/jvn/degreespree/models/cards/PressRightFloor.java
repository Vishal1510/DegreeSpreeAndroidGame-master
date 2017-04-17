package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/11/15.
 */
public class PressRightFloor extends Card {

    public PressRightFloor() {
        cardName = "Press the Right Floor";
        imageRef = R.drawable.pressrightfloor;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 16);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 4;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,2,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-2);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
