package com.sachin.CricketGame.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Wicket {
    private String ball;
    @JsonIgnore
    private Player batsman;
    @JsonIgnore
    private Bowler bowler;
    private String batsmanName;
    private String bowlerName;

    public Wicket(String ball,Player batsman,Bowler bowler){
        this.ball=ball;
        this.batsman=batsman;
        this.bowler=bowler;
    }
    public Wicket(String ball,String batsman,String bowler){
        this.ball= ball;
        this.batsmanName = batsman;
        this.bowlerName = bowler;
    }

    public String getBall() {
        return ball;
    }
    public Player getBatsman() {
        return batsman;
    }
    public Bowler getBowler() {
        return bowler;
    }

    public String getBatsmanName() {
        return batsmanName;
    }
    public String getBowlerName() {
        return bowlerName;
    }
}
