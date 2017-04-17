package com.jvn.degreespree.models;

/**
 * Created by vishal on 11/5/15.
 */
public class Reward {
    private int learning;
    private int craft;
    private int integrity;
    private int qualityPoints;
    private boolean correctPosition;
    private boolean metPrereqs;

    public Reward(int learning, int craft, int integrity, int qualityPoints, boolean correctPosition, boolean metPrereqs) {
        this.learning = learning;
        this.craft = craft;
        this.integrity = integrity;
        this.qualityPoints = qualityPoints;
        this.correctPosition = correctPosition;
        this.metPrereqs = metPrereqs;
    }

    public void add(int learning, int craft, int integrity, int qualityPoints) {
        this.learning += learning;
        this.craft += craft;
        this.integrity += integrity;
        this.qualityPoints += qualityPoints;
    }

    public boolean wasSuccess() {
        return (metPrereqs && correctPosition);
    }

    public int getLearning() {
        return learning;
    }

    public int getCraft() {
        return craft;
    }

    public int getIntegrity() {
        return integrity;
    }

    public int getQualityPoints() {
        return qualityPoints;
    }

    public boolean isCorrectPosition() {
        return correctPosition;
    }

    public boolean isMetPrereqs() {
        return metPrereqs;
    }

    @Override
    public String toString() {

        return  learning + " learning, "+  + craft + " craft, " + integrity + " integrity, " + qualityPoints + " quality points";

    }
}
