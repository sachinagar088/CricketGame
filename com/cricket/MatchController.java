package com.cricket;

public class MatchController {

    public void start() {
        Cricket c = new Cricket();
        Match match = new Match();
        TeamFactory teamFactory=new TeamFactory();
        Toss toss = new Toss();
        Team team1 = teamFactory.getTeams(1);
        Team team2 = teamFactory.getTeams(2);
        System.out.println(team1);
        System.out.println(team2);
        toss.tossCoin(c,team1,team2);
        System.out.println();
        if(c.getTeamBattingFirst()==team1.getTeamID()) {
            match.playMatch(c,team1,team2);
        }
        else{
            match.playMatch(c,team2,team1);
        }
        System.out.println("\nScore Card\n");
        match.matchSummary(team1);
        match.matchSummary(team2);
    }
}
