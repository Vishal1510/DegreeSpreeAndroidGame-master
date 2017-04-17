package com.jvn.degreespree.models;

import java.util.ArrayList;

/**
 * Created by vishal on 10/3/15.
 */
public class BoardPosition {
    private String positionName;
    private int index;
    private ArrayList<Integer> nearbyPositions;

    public BoardPosition(String name, int index, int[] nearbyPositions) {
        positionName = name;
        this.index = index;
        this.nearbyPositions = new ArrayList<>();
        for (int i = 0; i < nearbyPositions.length; i++) {
            this.nearbyPositions.add(nearbyPositions[i]);
        }

    }

    public String getPositionName() {
        return positionName;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return positionName;
    }

    public ArrayList<Integer> getNearbyPositions() {
        return nearbyPositions;
    }
}
