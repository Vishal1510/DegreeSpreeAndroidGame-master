package com.jvn.degreespree.models.cards;

/**
 * Created by vishal on 11/1/15.
 */
import com.jvn.degreespree.R;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/1/15.
 */
public class GoodbyeProfessor extends Card implements DiscardCallback{

    public GoodbyeProfessor() {
        cardName = "Room of Retirement";
        imageRef = R.drawable.goodbyeprofessor;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 17);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 6 && player.getCraft() >= 6 && player.getIntegrity() >= 6;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,0,0,10);
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
