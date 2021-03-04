package com.cricket;

public class MatchController {

    public static MatchController getMethod(){
        return new MatchController();
    }
    public void start() {
        Cricket c = Cricket.getCricket();
        MatchMethod m = MatchMethod.getMethod();
        TeamCreation t=TeamCreation.getInstance();
        Team t1 = t.add(1);
        Team t2 = t.add(2);
        t.view(t1);
        t.view(t2);
        c.setTotalOvers(2);
        c.setTotalWickets(2);
        c.setTeamBattingFirst(t1.getTeamID());
        System.out.println();
        m.battingFirst(c,t1);
        System.out.println();
        System.out.println("Team "+t1.getTeamID()+" scored "+t1.getTeamScore()+"/"+t1.getTotalWicketsFall());
        System.out.println("First Innings Over");
        System.out.println();
        m.battingSecond(c,t2);
    }
}
