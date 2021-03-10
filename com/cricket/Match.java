package com.cricket;

public class Match {

    public void playMatch(Cricket cricket,Team teamOne,Team teamTwo){
        Innings firstInnings = new Innings(teamOne,teamTwo,0);
        firstInnings.startInnings();
        cricket.setBattingFirstScore(teamOne.getTeamScore());
        System.out.println("Team "+teamOne.getTeamID()+" "+teamOne.getTeamName()+" scored "+teamOne.getTeamScore()+"/"+teamOne.getTotalWicketsFall());
        System.out.println("First Innings Over\n Innings Summary\n");
        ScoreCard.inningsSummary(firstInnings);
        Innings secondInnings = new Innings(teamTwo,teamOne,1);
        secondInnings.startInnings();
        ScoreCard.inningsSummary(secondInnings);
    }
    public void matchSummary(Team team){
        System.out.println("Team ID : "+ team.getTeamID());
        System.out.println("Team Name : "+team.getTeamName());
        System.out.format("%10s%10s%15s%10s%10s\n","Name","Role","RunsScored","Balls","Out");
        for(Player p : team.getPlayers()) {
            System.out.format("%12s%10s%10d%12d%10s\n",p.getName(),p.getPlayerRole(),p.getRunsScored(),p.getBallsTaken(),p.isOutStatus());
        }
        System.out.format("%10s%10s%15s%10s\n","Name","Overs","Wickets","RunsGiven");
        for(Bowler bowler : team.getBowlers()) {
            System.out.format("%12s%10s%10d%12d\n",bowler.getName(),bowler.getOversDone(),bowler.getWicketsTaken(),bowler.getRunsGiven());
        }
        System.out.println("Total Score : "+team.getTeamScore()+"/"+team.getTotalWicketsFall());
    }
}
