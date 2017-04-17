package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 12/13/15.
 */
public class CECS282 extends Card implements DiscardCallback {

    public CECS282() {
        cardName = "CECS282";
        imageRef = R.drawable.cecs282;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position >= 11);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getCraft() >= 8 && player.getIntegrity() >= 8 && player.getLearning() >= 8;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0, 0, 0, 5);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();

    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-2);
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
