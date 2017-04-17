package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 11/1/15.
 */
public class Phys151 extends Card {

    public Phys151() {
        cardName = "Physics 151";
        imageRef = R.drawable.phys151;
        year = Year.Freshman;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 17);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getCraft() >= 3;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,0,0,5);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-3);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
