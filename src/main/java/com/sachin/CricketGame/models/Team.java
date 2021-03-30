package com.sachin.CricketGame.models;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private int teamID;
    @NotEmpty(message = "Please Provide Team Name")
    private String teamName;
    private List<Player> players = new ArrayList<>();
    private List<Bowler> bowlers = new ArrayList<>();
    private int totalWicketsFall;
    private int teamScore;

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalWicketsFall() {
        return totalWicketsFall;
    }

    public void setTotalWicketsFall(int totalWicketsFall) {
        this.totalWicketsFall = totalWicketsFall;
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

    public List<Bowler> getBowlers() {
        return bowlers;
    }

    public void setBowlers(List<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

    public Bowler getBowler(int next){
        nextBowler nextBowler = new ChooseNextBowler();
        return nextBowler.SequentialNextBowler(bowlers,next);
    }

    public Player getNextBatsman(int next){
        nextBatsman nextBatsman = new ChooseNextBatsman();
        return nextBatsman.SequentialNextBatsman(players,next);
//        return players.get(next);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addBowler(Bowler bowler){
        bowlers.add(bowler);
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        for(Player player : players){
            s.append(player.getPlayerId()).append(" ").append(player.getName()).append(" ").append(player.getPlayerRole()).append("\n");
        }
        return "Team ID = " + teamID + "\n" +
                "Team Name = " + teamName + "\n" + s;
    }

}
