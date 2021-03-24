package com.sachin.CricketGame.models;

public class Ball {
    private String ballThrown;
    private int runsScored;
    private String batsman;
    private String bowler;

    private Ball(String ballThrown, int runsScored, String batsman, String bowler) {
        this.ballThrown = ballThrown;
        this.runsScored = runsScored;
        this.batsman = batsman;
        this.bowler = bowler;
    }

    public static Ball getInstance(String ballThrown, int runsScored, String batsman, String bowler){
        return new Ball(ballThrown,runsScored,batsman,bowler);
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
