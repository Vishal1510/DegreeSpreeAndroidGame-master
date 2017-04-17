package com.jvn.degreespree.models.cards;

/**
 * Created by vishal on 11/1/15.
 */
import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/1/15.
 */
public class LunchBratWurst extends Card {

    public LunchBratWurst() {
        cardName = "Lunch at Bratwurst Hall";
        imageRef = R.drawable.lunchbratwurst;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 9);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,1,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}