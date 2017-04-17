package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;

/**
 * Created by vishal on 11/11/15.
 */
public class ProfessorEnglert extends Card implements DiscardCallback, RewardCallback {

    public ProfessorEnglert() {
        cardName = "Professor Englert";
        imageRef = R.drawable.professorenglert;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 19);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getIntegrity() >= 3;
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
        if (playedBy.isHuman()) {
            controller.openDiscardDialog(playedBy, this);
        } else {
            ComputerPlayer cpu = (ComputerPlayer) playedBy;
            cpu.chooseDiscard();
            discardCallback();
        }
    }

    @Override
    public void discardCallback() {
        playedBy.endTurn();
    }

    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}