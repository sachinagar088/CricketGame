package com.cricket;

import java.util.List;

public class Team {
    private int teamID;
    private List<Player> players;
    private int totalWicketsFall;
    private int teamScore;

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getTotalWicketsFall() {
        return totalWicketsFall;
    }

    public void setTotalWicketsFall(int totalWicketsFall) {
        this.totalWicketsFall = totalWicketsFall;
    }

    public static Team getInstance(){
        return new Team();
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
