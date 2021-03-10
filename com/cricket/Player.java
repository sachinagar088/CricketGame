package com.cricket;

public class Player {
    private int playerId;
    private String name;
    private String playerRole;
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

    public int getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(int strikeRate) {
        this.strikeRate = strikeRate;
    }
}
