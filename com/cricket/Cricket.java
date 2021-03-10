package com.cricket;

public class Cricket {
    private int battingFirstScore;
    private int teamBattingFirst;
    private final static int noOfOvers = 2;
    private final static int totalWickets = 2;

    public int getTeamBattingFirst() {
        return teamBattingFirst;
    }

    public void setTeamBattingFirst(int teamBattingFirst) {
        this.teamBattingFirst = teamBattingFirst;
    }

    public int getBattingFirstScore() {
        return battingFirstScore;
    }

    public void setBattingFirstScore(int battingFirstScore) {
        this.battingFirstScore = battingFirstScore;
    }

    public static int getNoOfOvers() {
        return noOfOvers;
    }

    public static int getTotalWickets() {
        return totalWickets;
    }
}
