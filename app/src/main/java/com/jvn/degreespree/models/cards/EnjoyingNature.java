package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/12/15.
 */
public class EnjoyingNature extends Card {

    public EnjoyingNature() {
        cardName = "Enjoying Nature";
        imageRef = R.drawable.enjoyingnature;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position < 11);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,1,0,0);
        BoardPosition position = controller.getGameBoard().getPosition(20);
        controller.movePlayer(position);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
