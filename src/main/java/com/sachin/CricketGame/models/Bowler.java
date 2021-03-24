package com.sachin.CricketGame.models;

public class Bowler extends Player{

    private int wicketsTaken;
    private int runsGiven;
    private float oversDone;

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }

    public float getOversDone() {
        return oversDone;
    }

    public void setOversDone(float oversDone) {
        this.oversDone = oversDone;
    }
}
