package com.sachin.CricketGame.models;

public class ScoreCard {

    public static void inningsSummary(Innings innings){
        int k=1;
        System.out.println("Overs Done : "+innings.getOversDone());
        System.out.println("Wickets Taken : "+innings.getWicket());
        System.out.format("%10s%10s%15s\n","Over","Wickets","Runs");
        for(Over over : innings.getOvers()){
            System.out.format("%10d%10d%15d\n",k++,over.getWicketsTaken(),over.getRunsScored());
        }
        System.out.format("\n%10s\n","Wickets Summary");
        System.out.format("%10s%15s%15s\n","Ball","Batsman Out","Bowler");
        for(Wicket wicket : innings.getWickets()){
            System.out.format("%10s%10s%15s\n",wicket.getBall(),wicket.getBatsman().getName(),wicket.getBowler().getName());
        }
    }
    public static void teamSummary(Team teamOne,Team teamTwo){
        System.out.println("Team "+teamOne.getTeamName()+" Score : " +teamOne.getTeamScore()+"/"+teamOne.getTotalWicketsFall());
        System.out.format("%10s%15s%15s%15s%10s\n","Name","RunsScored","Balls","Strike Rate","Out");
        for(Player p : teamOne.getPlayers()) {
            System.out.format("%12s%10s%10d%13d%12b\n",p.getName(),p.getRunsScored(),p.getBallsTaken(),p.getStrikeRate(),p.isOutStatus());
        }
        System.out.println("\nTeam " + teamTwo.getTeamName() + " Balling Summary");
        System.out.format("%10s%10s%15s%10s\n","Name","Overs","Wickets","RunsGiven");
        for(Bowler bowler : teamTwo.getBowlers()) {
            System.out.format("%12s%10s%10d%12d\n",bowler.getName(),bowler.getOversDone(),bowler.getWicketsTaken(),bowler.getRunsGiven());
        }

    }
}
