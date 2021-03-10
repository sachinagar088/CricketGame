package com.cricket;

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
    public void teamSummary(Team team){
        System.out.println("Team ID : "+ team.getTeamID());
        System.out.println("Team Name : "+team.getTeamName());
        System.out.format("%10s%10s%15s%10s\n","Name","Role","RunsScored","Balls");
        for(Player p : team.getPlayers()) {
            System.out.format("%12s%10s%10d%12d\n",p.getName(),p.getPlayerRole(),p.getRunsScored(),p.getBallsTaken());
        }
        System.out.format("%10s%10s%15s%10s\n","Name","Overs","Wickets","RunsGiven");
        for(Bowler bowler : team.getBowlers()) {
            System.out.format("%12s%10s%10d%12d\n",bowler.getName(),bowler.getOversDone(),bowler.getWicketsTaken(),bowler.getRunsGiven());
        }
        System.out.println("Total Score : "+team.getTeamScore()+"/"+team.getTotalWicketsFall());
    }
    public void ballSummary(Innings innings){
        System.out.format("%10s%10s%15s%10s\n","Ball","Runs","Batsman","Bowler");
        for(Over over : innings.getOvers()){
            for(Ball ball : over.getBalls()){
                System.out.format("%10s%10d%15s%10s\n",ball.getBallThrown(),ball.getRunsScored(),ball.getBatsman(),ball.getBowler());
            }
        }
    }

}
