package com.sachin.CricketGame.models;

public class Player {
    private int playerId;
    private String name;
    private String playerRole;
    private int avgStrikeRate;
    private int noOfMatches;
    private int avgRuns;
    private int runsScored;
    private int ballsTaken;
    private int strikeRate;
    private boolean outStatus;

    public int getBallsTaken() {
        return ballsTaken;
    }

    public void setBallsTaken(int ballsTaken) {
        this.ballsTaken = ballsTaken;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public boolean isOutStatus() {
        return outStatus;
    }

    public void setOutStatus(boolean outStatus) {
        this.outStatus = outStatus;
    }

    public int getAvgStrikeRate() {
        return avgStrikeRate;
    }

    public void setAvgStrikeRate(int avgStrikeRate) {
        this.avgStrikeRate = avgStrikeRate;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getAvgRuns() {
        return avgRuns;
    }

    public void setAvgRuns(int avgRuns) {
        this.avgRuns = avgRuns;
    }

    public int getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(int strikeRate) {
        this.strikeRate = strikeRate;
    }

    public void calculateStrikeRate() {
        strikeRate = (int)(((float) runsScored / (float) ballsTaken) * 100);
    }
}
