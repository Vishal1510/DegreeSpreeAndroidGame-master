package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

import java.util.Random;

/**
 * Created by vishal on 12/13/15.
 */
public class GreenTornado extends Card {

    public GreenTornado() {
        cardName = "Green Tornado";
        imageRef = R.drawable.tornado;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position <= 10);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        Random r = new Random();
        playedBy.rewardPlayer(reward);
        BoardPosition position = controller.getGameBoard().getPosition(r.nextInt(20));
        controller.movePlayer(position);
        playedBy.endTurn();

    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();

    }
}
