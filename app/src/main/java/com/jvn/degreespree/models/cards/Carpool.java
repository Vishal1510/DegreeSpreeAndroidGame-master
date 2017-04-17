package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;


/**
 * Created by vishal on 12/13/15.
 */
public class Carpool extends Card {

    public Carpool() {
        cardName = "Car Pool";
        imageRef = R.drawable.carpool;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 2);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getCraft() >= 5;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0, 0, 0, 3);
        controller.drawCard();
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();

    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-1);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();

    }
}
