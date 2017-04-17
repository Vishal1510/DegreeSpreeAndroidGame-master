package com.jvn.degreespree.models.cards;

/**
 * Created by vishal on 11/1/15.
 */
import android.util.Log;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 11/1/15.
 */
public class Kin253 extends Card {

    public Kin253() {
        cardName = "KIN 253";
        imageRef = R.drawable.kin253;
        year = Year.Freshman;
    }

    private void fail(Player player) {
        BoardPosition position = controller.getGameBoard().getPosition(13);
        controller.movePlayer(position);
        player.endTurn();
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 0);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getIntegrity() >= 4;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,2,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
