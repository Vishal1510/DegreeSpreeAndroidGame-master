package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;

/**
 * Created by vishal on 11/12/15.
 */
public class LearningLinux extends Card implements RewardCallback {

    public LearningLinux() {
        cardName = "Learning Linux";
        imageRef = R.drawable.learninglinux;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 11);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getCraft() >= 2 && player.getIntegrity() >= 3;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,0,0,3);
        if (playedBy.isHuman()) {
            controller.openRewardDialog(1, true, true, true, this, reward);
        } else {
            ((ComputerPlayer) playedBy).pickReward(1, true, true, true, reward);
            playedBy.rewardPlayer(reward);
            playedBy.endTurn();
        }

    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-1);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
