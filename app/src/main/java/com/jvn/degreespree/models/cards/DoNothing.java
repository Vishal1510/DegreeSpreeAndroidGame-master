package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;
import com.jvn.degreespree.models.Year;

/**
 * Created by vishal on 12/13/15.
 */
public class DoNothing extends Card {

    public DoNothing() {
        cardName = "Do Nothing";
        imageRef = R.drawable.donothing;
        year = Year.Sophomore;
    }

    @Override
    protected boolean correctPosition(Player player) {
        return true;
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        playedBy.endTurn();

    }

    @Override
    protected void fail(Reward reward) {
        playedBy.endTurn();

    }
}
