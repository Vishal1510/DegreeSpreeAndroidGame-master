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
public class LearnNetBeans extends Card {

    public LearnNetBeans() {
        cardName = "Netbeans";
        imageRef = R.drawable.learnnetbeans;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 20);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 3;
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
