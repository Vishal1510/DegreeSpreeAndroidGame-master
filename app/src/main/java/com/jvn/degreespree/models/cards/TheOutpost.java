package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;

/**
 * Created by vishal on 11/11/15.
 */
public class TheOutpost extends Card implements RewardCallback {

    public TheOutpost() {
        cardName = "The Outpost";
        imageRef = R.drawable.theoutpost;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position < 11 && position != 5);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
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
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}