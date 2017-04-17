package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 11/1/15.
 */
public class Math122 extends Card implements RewardCallback{

    public Math122() {
        cardName = "Math 122";
        imageRef = R.drawable.math122;
        year = Year.Freshman;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 7);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        if (playedBy.isHuman()) {
            controller.openRewardDialog(1, true, false, true, this, reward);
        } else {
            ((ComputerPlayer) playedBy).pickReward(1, true, false, true, reward);
            playedBy.rewardPlayer(reward);
            playedBy.endTurn();
        }

    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
