package com.jvn.degreespree.fragments.view.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.DiscardCallback;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;

/**
 * Created by vishal on 11/7/15.
 */
public class DiscardDialog extends DialogFragment {
    private GameController controller;
    private Player player;
    private DiscardCallback callback;

    private Button mDiscard;
    private ImageView cardDisplay;
    private Integer cardIndex;
    private ArrayList<Card> cards;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        cardIndex = 0;
        cards = player.getCards();




        View v = inflater.inflate(R.layout.discard_fragment, null);
        mDiscard = (Button) v.findViewById(R.id.discard_button);

        mDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.discard(cards.get(cardIndex));

                DiscardDialog.this.dismiss();

                if (callback != null) {
                    callback.discardCallback();
                }
            }
        });

        cardDisplay = (ImageView) v.findViewById(R.id.discard_display);

        cardDisplay.setImageResource(cards.get(cardIndex).getImageRef());

        cardDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Card nextCard = getNextCard();
                cardDisplay.setImageResource(nextCard.getImageRef());


            }
        });

        builder.setView(v);


        return builder.create();
    }

    public void setPlayer(Player player, DiscardCallback callback) {

        this.player = player;
        this.callback = callback;

    }

    public void bind(GameController controller) {
        this.controller = controller;
    }

    private Card getNextCard() {

        cardIndex = (cardIndex + 1) % cards.size();
        return cards.get(cardIndex);

    }


}
