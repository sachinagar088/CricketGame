package com.sachin.CricketGame.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

public class Cricket {
    //will be removed
    private int match_id;
    @JsonIgnore
    private int teamBattingFirst;
    private Team teamOne;
    private Team teamTwo;
    private Innings innings_one;
    private Innings innings_two;
    private Team winning_team;
    private String comments;
    @NotEmpty(message = "Enter the Venue")
    private String venue;
    private Date date;
    private String status;
    private final static int noOfOvers = 2;
    //will be removed
    private final static int totalWickets = 2;

    public int getTeamBattingFirst() {
        return teamBattingFirst;
    }

    public void setTeamBattingFirst(int teamBattingFirst) {
        this.teamBattingFirst = teamBattingFirst;
    }

    public static int getNoOfOvers() {
        return noOfOvers;
    }

    public static int getTotalWickets() {
        return totalWickets;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Innings getInnings_one() {
        return innings_one;
    }

    public void setInnings_one(Innings innings_one) {
        this.innings_one = innings_one;
    }

    public Innings getInnings_two() {
        return innings_two;
    }

    public void setInnings_two(Innings innings_two) {
        this.innings_two = innings_two;
    }

    public Team getWinning_team() {
        return winning_team;
    }

    public void setWinning_team(Team winning_team) {
        this.winning_team = winning_team;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
