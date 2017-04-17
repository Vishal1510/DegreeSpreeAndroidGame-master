package com.jvn.degreespree.fragments.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Deck;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.TurnInfo;
import com.jvn.degreespree.models.Year;
import com.jvn.degreespree.models.cards.Card;
import com.jvn.degreespree.widgets.StatsRow;

import java.util.ArrayList;

/**
 * Created by vishal on 9/23/15.
 */
public class GameplayViewFragment extends Fragment {

    private String TAG = "GameplayViewFragment";
    private GameController controller;

    private Button mMove;
    private Button mShowBoard;
    private Button mPlayCard;
    private Button mDrawCard;
    private ListView mMovableLocationLists;
    private ArrayAdapter<BoardPosition> movableLocations;
    private ImageView mCardView;
    private Integer currentCard = null;
    private TextView mCardsInDeck;
    private TextView mCardsOutPlay;
    private TextView mYear;

    private BoardPosition currentlySelected = null;

    private boolean moveEnabled = false;
    private boolean drawEnabled = false;
    private boolean playEnabled = false;

    private StatsRow mPlayer1Score;
    private StatsRow mPlayer2Score;
    private StatsRow mPlayer3Score;
    private TextView mPlayerInfo;

    private ArrayAdapter<TurnInfo> turnsTaken;
    private ListView mTurnsTaken;

    public void bind(GameController controller) {
        this.controller = controller;
    }

    public void init() {
        initMoveList();
        initTurnsTaken();
    }

    private void initMoveList() {
        movableLocations = new ArrayAdapter<BoardPosition>(controller.getApplicationContext(), R.layout.list_item_black, new ArrayList<BoardPosition>());
    }

    private void initTurnsTaken() {
        turnsTaken = new ArrayAdapter<TurnInfo>(controller.getApplicationContext(), R.layout.turn_info_item, new ArrayList<TurnInfo>());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_menu_fragment, container, false);
        init(v);
        mMovableLocationLists.setBackground(getResources().getDrawable(R.drawable.text_box_border));
        return v;
    }

    private void init(View v) {
        initShowBoardButton(v);
        initMoveButton(v);
        initMoveList(v);
        initPlayCard(v);
        initCardDisplay(v);
        initDrawCard(v);
        initNextCard(v);
        initScoreBoard(v);
        initTurnsInfo(v);
    }

    private void initDrawCard(View v) {
        mDrawCard = (Button) v.findViewById(R.id.draw_card);

        mDrawCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controller.drawCard();
            }
        });

        mDrawCard.setEnabled(drawEnabled);

    }

    private void initNextCard(View v) {

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.nextCard();
            }
        });
    }

    private void initShowBoardButton(View v) {
        mShowBoard = (Button) v.findViewById(R.id.show_board);

        mShowBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.viewBoard();
            }
        });
    }

    private void initMoveButton(View v) {
        mMove = (Button) v.findViewById(R.id.move);
        mMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentlySelected != null) {
                    controller.movePlayer(currentlySelected);
                    currentlySelected = null;
                }
            }
        });

        mMove.setEnabled(moveEnabled);
    }

    private void initPlayCard(View v) {
        mPlayCard = (Button) v.findViewById(R.id.play_card);
        mPlayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing for now
                controller.playCard(null);
            }
        });
        mPlayCard.setEnabled(playEnabled);
    }

    private void initMoveList(View v) {
        mMovableLocationLists = (ListView) v.findViewById(R.id.movable_locations);
        mMovableLocationLists.setAdapter(movableLocations);

        mMovableLocationLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentlySelected = (BoardPosition) adapterView.getItemAtPosition(i);
                Log.d(TAG, currentlySelected.getPositionName());
            }
        });
    }

    private void initCardDisplay(View v) {
        mCardView = (ImageView) v.findViewById(R.id.card_view);
        if (currentCard != null) {
            mCardView.setImageResource(currentCard);
        }
    }

    private void initScoreBoard(View v) {
        mPlayer1Score = (StatsRow) v.findViewById(R.id.player1);
        mPlayer2Score = (StatsRow) v.findViewById(R.id.player2);
        mPlayer3Score = (StatsRow) v.findViewById(R.id.player3);

        mCardsInDeck = (TextView) v.findViewById(R.id.cards_in_deck);
        mCardsOutPlay = (TextView) v.findViewById(R.id.cards_out_play);
        mYear = (TextView) v.findViewById(R.id.year);

        mPlayerInfo = (TextView) v.findViewById(R.id.current_player);

        controller.updatePlayerInfo();
        controller.updateScores();
    }

    private void initTurnsInfo(View v) {
        mTurnsTaken = (ListView) v.findViewById(R.id.turns_info);
        mTurnsTaken.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        mTurnsTaken.setAdapter(turnsTaken);
    }

    public void updateMovableLocation(ArrayList<BoardPosition> positions) {
        movableLocations.clear();
        movableLocations.addAll(positions);
        movableLocations.notifyDataSetChanged();
    }

    public void updateCardDisplay(Card card) {
        if (card != null) {
            currentCard = card.getImageRef();
            // safety check.  Card could be updated while out of context.  Have to do this because I goofed
            // the way I built the view.
            if (mCardView != null) {
                mCardView.setImageResource(card.getImageRef());
            }
        } else {
            if (mCardView != null) {
                mCardView.setImageResource(R.drawable.nocard);
            }
        }

    }

    public void disableMove() {
        moveEnabled = false;
        if (mMove != null) mMove.setEnabled(moveEnabled);

    }

    public void enableMove() {
        moveEnabled = true;
        if (mMove != null) mMove.setEnabled(moveEnabled);
    }

    public void enableDrawCard() {
        drawEnabled = true;
        if (mDrawCard != null) mDrawCard.setEnabled(drawEnabled);
    }

    public void disableDrawCard() {
        drawEnabled = false;
        if (mDrawCard != null) mDrawCard.setEnabled(drawEnabled);
    }

    public void enablePlayCard() {
        playEnabled = true;
        if (mPlayCard != null) mPlayCard.setEnabled(playEnabled);
    }

    public void disablePlayCard() {
        playEnabled =false;
        if (mPlayCard != null) mPlayCard.setEnabled(playEnabled);
    }

    public void disableUi() {
        disableDrawCard();
        disableMove();
        disablePlayCard();
    }

    public void updateScoreBoard(ArrayList<Player> players, Deck deck, Year year) {
        if (mPlayer1Score != null) {
            mPlayer1Score.update(players.get(0));
        }
        if (mPlayer2Score != null) {
            mPlayer2Score.update(players.get(1));
        }
        if (mPlayer3Score != null) {
            mPlayer3Score.update(players.get(2));
        }

        if (mCardsInDeck != null) {
            mCardsInDeck.setText(deck.inPlay() + "");
        }

        if (mCardsOutPlay != null) {
            mCardsOutPlay.setText(deck.outPlay() + "");
        }

        if (mYear != null) {
            mYear.setText("Year: " + year.toString());
        }
    }

    public void addTurnInfo(TurnInfo info) {
        turnsTaken.add(info);

    }

    public void updatePlayerInfo(Player player) {
        if (mPlayerInfo != null) {
            if (player.isHuman()) {
                mPlayerInfo.setText("Human player is " + player.getPlayerName() + " and is in " + player.getBoardPosition().toString());
            } else {
                mPlayerInfo.setText("Computer player is " + player.getPlayerName() + " and is in " + player.getBoardPosition().toString());
            }

        }
    }
}
