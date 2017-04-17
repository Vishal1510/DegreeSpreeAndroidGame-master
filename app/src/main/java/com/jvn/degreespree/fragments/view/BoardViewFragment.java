package com.jvn.degreespree.fragments.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.Utils.ScreenUtils;
import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.widgets.PlayerIcon;
import com.jvn.degreespree.widgets.ScrollableRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vishal on 9/23/15.
 */
public class BoardViewFragment extends Fragment {

    private static final String TAG = "BoardViewFragment";
    private GameController controller;
    private Context applicationContext;

    private Button mGameMenu;
    private ArrayList<LinearLayout> mBoardSquares;

    private HashMap<Player, PlayerIcon> playerIcons = new HashMap<>(8);

    public void bind(GameController controller) {
        this.controller = controller;
    }

    public void init() {
        initBoardSquares();
    }

    private void initBoardSquares() {
        mBoardSquares = new ArrayList<>(21);

        mBoardSquares.add(createBoardSquare(20,100,0,0,400,620));  //George Allen Field
        mBoardSquares.add(createBoardSquare(20,25,400,0,500,240));  //Japanese Garden
        mBoardSquares.add(createBoardSquare(20,50,900,0,700,480));  //Student Parking
        mBoardSquares.add(createBoardSquare(20,25,400,250,500,240));  //Pyramid
        mBoardSquares.add(createBoardSquare(20,50,0,620,300,850));  //West Walkway
        mBoardSquares.add(createBoardSquare(20,50,400,500,580,350));  //Rec Center
        mBoardSquares.add(createBoardSquare(20,50,1000,500,660,350));  //Forbidden parking
        mBoardSquares.add(createBoardSquare(20,50,0,1600,450,400));  //Library
        mBoardSquares.add(createBoardSquare(20,50,450,1600,550,400));  //LA 5
        mBoardSquares.add(createBoardSquare(20,50,1000,1600,650,400));  //Bratwurst Hall
        mBoardSquares.add(createBoardSquare(20,10,1440,930,170,650));  //East Walkway
        mBoardSquares.add(createBoardSquare(20,50,150,860,400,270));  //Computer Lab
        mBoardSquares.add(createBoardSquare(20,10,150,1130,650,170));  //North Hall
        mBoardSquares.add(createBoardSquare(20,50,150,1320,400,270));  //Room of Retirement
        mBoardSquares.add(createBoardSquare(20,50,580,860,400,270));  //ECS 302
        mBoardSquares.add(createBoardSquare(20,10,800,1130,650,170));  //South Hall
        mBoardSquares.add(createBoardSquare(20,50,580,1320,220,270));  //Elevators
        mBoardSquares.add(createBoardSquare(20,50,800,1320,400,270));  //ECS 308
        mBoardSquares.add(createBoardSquare(20,50,1020,860,220,270));  //Eat Club
        mBoardSquares.add(createBoardSquare(20,50,1230,860,220,270));  //Conference Room
        mBoardSquares.add(createBoardSquare(20,50,1200,1320,220,270));  //Lactation Lounge

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "created ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_board_fragment, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        mGameMenu = (Button) v.findViewById(R.id.player_menu);

        mGameMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.viewGameMenu();
            }
        });

        initBoardSquares(v);
    }



    private void initBoardSquares(View v) {
        ScrollableRelativeLayout board = (ScrollableRelativeLayout) v.findViewById(R.id.board);

        for (LinearLayout position : mBoardSquares) {
            if (position.getParent() != null) {
                ((ScrollableRelativeLayout)position.getParent()).removeView(position);
            }
            board.addView(position, position.getLayoutParams());
        }
    }

    private LinearLayout createBoardSquare(int paddingLeft, int paddingTop, int marginLeft, int marginTop, int width, int height) {
        LinearLayout layout = new LinearLayout(controller.getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(ScreenUtils.convertToDP(paddingLeft), ScreenUtils.convertToDP(paddingTop), 0, 0);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ScreenUtils.convertToDP(width), ScreenUtils.convertToDP(height));
        params.setMargins(ScreenUtils.convertToDP(marginLeft),ScreenUtils.convertToDP(marginTop),0,0);
        params.width = ScreenUtils.convertToDP(width);
        params.height = ScreenUtils.convertToDP(height);
        layout.setLayoutParams(params);

        return layout;

    }

    public void addPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
    }

    public void addPlayer(Player player) {
        PlayerIcon icon = new PlayerIcon(controller.getApplicationContext());
        icon.setup(player);
        playerIcons.put(player, icon);

        movePlayer(player);
    }

    public void movePlayer(Player player) {
        PlayerIcon icon = playerIcons.get(player);

        if (icon.getParent() != null) {
            LinearLayout parent = (LinearLayout)icon.getParent();
            parent.removeView(icon);
        }

        mBoardSquares.get(player.getBoardPosition().getIndex()).addView(icon);
    }

}
