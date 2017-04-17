package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/11/15.
 */
public class SoccerGoalie extends Card {

    public SoccerGoalie() {
        cardName = "Soccer Goalie";
        imageRef = R.drawable.soccergoalie;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 0);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 3 && player.getCraft() >= 3;
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
        BoardPosition position = controller.getGameBoard().getPosition(2);
        controller.movePlayer(position);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

}

