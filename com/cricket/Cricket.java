package com.cricket;
import java.util.Random;

public class Cricket {
    private int totalOvers;
    private int totalWickets;
    private int battingFirstScore;
    private int teamBattingFirst;
    private int totalWicketsBattingFirst;

    public int getTotalWicketsBattingFirst() {
        return totalWicketsBattingFirst;
    }

    public void setTotalWicketsBattingFirst(int totalWicketsBattingFirst) {
        this.totalWicketsBattingFirst = totalWicketsBattingFirst;
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
    public static Cricket getCricket(){
        return new Cricket();
    }
    public int randomNumbers(){
        int[] arr={0,1,2,3,4,6,7};
        return arr[new Random().nextInt(arr.length)];
    }
    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }
}
