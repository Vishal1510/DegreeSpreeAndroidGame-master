package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 12/13/15.
 */
public class CECS285 extends Card implements RewardCallback {

    public CECS285() {
        cardName = "CECS285";
        imageRef = R.drawable.cecs285;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 1 || position == 18);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 6;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0, 0, 0, 5);
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
        reward.add(0,0,0,-3);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }


    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}

