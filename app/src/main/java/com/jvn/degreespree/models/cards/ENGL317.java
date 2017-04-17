package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 12/13/15.
 */
public class ENGL317 extends Card {

    public ENGL317() {
        cardName = "ENGL 317";
        imageRef = R.drawable.engl317;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 8);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getCraft() >= 6;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0, 0, 0, 5);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        BoardPosition position = controller.getGameBoard().getPosition(2);
        controller.movePlayer(position);
        playedBy.endTurn();
    }
}

