package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 12/13/15.
 */
public class PHIL270 extends Card {

    public PHIL270() {
        cardName = "PHIL 270";
        imageRef = R.drawable.phil270;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 7 || position == 8);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getIntegrity() >= 7;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(1, 0, 0, 3);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}

