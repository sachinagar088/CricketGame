package com.cricket;

public class Player {
    private int playerId;
    private String name;
    private String playerRole;
    private int runsScored;
    private int ballsTaken;
    private boolean out;

    public int getBallsTaken() {
        return ballsTaken;
    }

    public void setBallsTaken(int ballsTaken) {
        this.ballsTaken = ballsTaken;
    }

    public static Player getInstance(){
        return new Player();
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

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
}
