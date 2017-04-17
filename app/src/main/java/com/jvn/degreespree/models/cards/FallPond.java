package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.RewardCallback;

/**
 * Created by vishal on 11/11/15.
 */
public class FallPond extends Card implements RewardCallback {

    public FallPond() {
        cardName = "Fall in the Pond";
        imageRef = R.drawable.fallpond;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 1);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 3;
    }

    @Override
    protected void success(Reward reward) {
        controller.openRewardDialog(1, true, true, false, this, reward);
    }

    @Override
    protected void fail(Reward reward) {
        BoardPosition position = controller.getGameBoard().getPosition(20);
        controller.movePlayer(position);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    public void rewardCallback(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}

