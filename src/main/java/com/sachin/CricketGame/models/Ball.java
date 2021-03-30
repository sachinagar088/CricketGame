package com.sachin.CricketGame.models;

public class Ball {
    private String ballThrown;
    private int runsScored;
    private String batsman;
    private String bowler;

    public Ball(String ballThrown, int runsScored, String batsman, String bowler) {
        this.ballThrown = ballThrown;
        this.runsScored = runsScored;
        this.batsman = batsman;
        this.bowler = bowler;
    }

    public String getBallThrown() {
        return ballThrown;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getBowler() {
        return bowler;
    }
}
