package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/11/15.
 */
public class MeetDean extends Card implements DiscardCallback {

    public MeetDean() {
        cardName = "Meet The Dean";
        imageRef = R.drawable.meet_the_dean;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 12 || position == 15);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getIntegrity() >= 3 && player.getLearning() >= 3 && player.getCraft() >= 3;
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

}
