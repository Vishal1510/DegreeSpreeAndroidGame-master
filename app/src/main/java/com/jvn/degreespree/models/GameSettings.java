package com.jvn.degreespree.models;

import com.jvn.degreespree.Utils.RandomPlayer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vishal on 10/9/15.
 */
public class GameSettings {

    ArrayList<Player> players;

    public GameSettings(Player player1, Player player2, Player player3) {
        players = new ArrayList<>(3);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Collections.shuffle(players);

    }

    public GameSettings() {
        players = new ArrayList<>(3);
        RandomPlayer.reset();
        Player player1 = new HumanPlayer(RandomPlayer.getName());
        Player player2 = new ComputerPlayer(RandomPlayer.getName());
        Player player3 = new ComputerPlayer(RandomPlayer.getName());

        players.add(player1);
        players.add(player2);
        players.add(player3);

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
