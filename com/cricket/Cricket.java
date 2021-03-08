package com.cricket;

public class Cricket {
    private int totalOvers;
    private int totalWickets;
    private int battingFirstScore;
    private int teamBattingFirst;

    public static Cricket getInstance(){
        return new Cricket();
    }
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

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }
}
