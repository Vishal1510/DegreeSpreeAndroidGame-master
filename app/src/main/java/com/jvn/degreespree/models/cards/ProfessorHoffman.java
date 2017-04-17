package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by vishal on 11/11/15.
 */
public class ProfessorHoffman extends Card {

    public ProfessorHoffman() {
        cardName = "Proffessor Hoffman";
        imageRef = R.drawable.professorhoffman;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position != 20);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return player.getLearning() >= 3;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,0,0,5);
        controller.drawCard();
        controller.drawCard();
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-5);
        BoardPosition position = controller.getGameBoard().getPosition(20);
        controller.movePlayer(position);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
