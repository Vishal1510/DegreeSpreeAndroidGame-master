package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 11/11/15.
 */
public class ElectiveClass extends Card implements RewardCallback {

    public ElectiveClass() {
        cardName = "Elective Class";
        imageRef = R.drawable.electiveclass;
        year = Year.Freshman;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 7);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 2;
    }

    @Override
    protected void success(Reward reward) {
        controller.drawCard();
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
        reward.add(0,0,0,-2);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
