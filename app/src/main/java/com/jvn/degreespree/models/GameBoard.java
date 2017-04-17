package com.jvn.degreespree.models;

import java.util.ArrayList;

/**
 * Created by vishal on 10/9/15.
 */
public class GameBoard {

    private ArrayList<BoardPosition> boardPositions;

    public GameBoard() {
        initPositions();
    }

    private void initPositions() {
        boardPositions = new ArrayList<>(21);

        boardPositions.add(new BoardPosition("George Allen Field", 0, new int[] {1, 3, 4, 5}));
        boardPositions.add(new BoardPosition("Japanese Garden", 1, new int[] {0, 3, 2}));
        boardPositions.add(new BoardPosition("Student Parking", 2, new int[] {1,3,5,6}));
        boardPositions.add(new BoardPosition("The Pyramid", 3, new int[] {0,1,2,5}));
        boardPositions.add(new BoardPosition("West Walkway", 4, new int[] {0, 5, 7, 12}));
        boardPositions.add(new BoardPosition("Rec Center", 5, new int[] {0, 3, 4, 6}));
        boardPositions.add(new BoardPosition("Forbidden Parking", 6, new int[] {2,5,10}));
        boardPositions.add(new BoardPosition("Library", 7, new int[] {4,8}));
        boardPositions.add(new BoardPosition("LA 5", 8, new int[] {7,9}));
        boardPositions.add(new BoardPosition("Bratwurst Hall", 9, new int[] {8,10}));
        boardPositions.add(new BoardPosition("East Walkway", 10, new int[] {6,9,15}));
        boardPositions.add(new BoardPosition("Computer Lab", 11, new int[] {12}));
        boardPositions.add(new BoardPosition("North Hall", 12, new int[] {4,11,13,14,15,16}));
        boardPositions.add(new BoardPosition("Room Of Retirement", 13, new int[] {12}));
        boardPositions.add(new BoardPosition("ECS 302", 14, new int[] {12,15}));
        boardPositions.add(new BoardPosition("South Hall", 15, new int[] {10, 12, 14, 17, 18, 19, 20}));
        boardPositions.add(new BoardPosition("Elevators", 16, new int[] {12}));
        boardPositions.add(new BoardPosition("ECS 308", 17, new int[] {15}));
        boardPositions.add(new BoardPosition("Eat Club", 18, new int[] {15}));
        boardPositions.add(new BoardPosition("CECS Conference Room", 19, new int[] {15}));
        boardPositions.add(new BoardPosition("Lactation Lounge", 20, new int[] {15}));

    }

    public BoardPosition getPosition(int index) {
        return boardPositions.get(index);
    }

    public ArrayList<BoardPosition> getPositions(ArrayList<Integer> indices) {
        ArrayList<BoardPosition> positions = new ArrayList<>();
        for (Integer index : indices) {
            positions.add(boardPositions.get(index));
        }
        return positions;
    }
}
