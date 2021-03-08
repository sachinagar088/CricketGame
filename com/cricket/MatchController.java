package com.cricket;

public class MatchController {

    public static MatchController getInstance(){
        return new MatchController();
    }
    public void start() {
        Cricket c = Cricket.getInstance();
        Match match = Match.getInstance();
        TeamFactory teamFactory=TeamFactory.getInstance();
        Toss toss = Toss.getInstance();
        Team t1 = teamFactory.getTeams(1);
        Team t2 = teamFactory.getTeams(2);
        System.out.println(t1);
        System.out.println(t2);
        c.setTotalOvers(2);
        c.setTotalWickets(2);
        toss.tossCoin(c,t1,t2);
        System.out.println();
        if(c.getTeamBattingFirst()==t1.getTeamID()) {
            match.playMatch(c,t1,t2);
        }
        else{
            match.playMatch(c,t2,t1);
        }
        System.out.println("\nScore Card\n");
        match.matchSummary(t1);
        match.matchSummary(t2);
    }
}
