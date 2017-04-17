package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

import java.util.Random;

/**
 * Created by vishal on 12/13/15.
 */
public class Mystery extends Card {

    public Mystery() {
        cardName = "Mystery";
        imageRef = R.drawable.mystery;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        return true;
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        Random r = new Random();
        reward.add(r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10));
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();

    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();

    }
}
