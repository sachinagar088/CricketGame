package com.cricket;

import java.util.ArrayList;
import java.util.List;

public class Over {
    private static final int noOfBalls=6;
    private int runsScored;
    private int wicketsTaken;
    private List<Ball> balls=new ArrayList<>(noOfBalls);

    public void addBall(Ball ball){
        balls.add(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public static Over getInstance(){
        return new Over();
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }
}
