package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 12/13/15.
 */
public class CECS274 extends Card {

    public CECS274() {
        cardName = "CECS274";
        imageRef = R.drawable.cecs274;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 14 || position == 17 || position == 11);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 7;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0, 0, 0, 5);
        controller.drawCard();
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
