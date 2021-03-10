package com.cricket;

public class Wicket {
    private String ball;
    private Player batsman;
    private Bowler bowler;

    public Wicket(String ball,Player batsman,Bowler bowler){
        this.ball=ball;
        this.batsman=batsman;
        this.bowler=bowler;
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
}
