package com.cricket;

public class Match {
    public static Match getInstance(){
        return new Match();
    }
    public String printBall(int i,int j){
        return (i)+"."+(j);

    }
    public void playMatch(Cricket cricket,Team teamOne,Team teamTwo){
        startInnings(cricket,teamOne,teamTwo,0);
        cricket.setBattingFirstScore(teamOne.getTeamScore());
        System.out.println("Team "+teamOne.getTeamID()+" "+teamOne.getTeamName()+" scored "+teamOne.getTeamScore()+"/"+teamOne.getTotalWicketsFall());
        System.out.println("First Innings Over");
        startInnings(cricket,teamTwo,teamOne,1);
    }
    public void startInnings(Cricket cricket,Team team,Team teamTwo,int chasing){
        int wicket=0;
        int totalScore=0;
        int win=0;
        Player striker= team.getPlayers().get(0);
        Player nonStriker = team.getPlayers().get(1);
        outer:for(int i=0;i<cricket.getTotalOvers();i++){
            Bowler currentBowler = teamTwo.getBowler();
            System.out.println(currentBowler.getName()+" "+"is balling the "+(i+1)+" Over");
            for(int j=0;j<6;j++){
                int score1 = Util.randomNumbers();
                striker.setBallsTaken(striker.getBallsTaken()+1);
                if(score1==7){
                    wicket+=1;
                    System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                    if(wicket==cricket.getTotalWickets()){
                        if((chasing==1 && totalScore < teamTwo.getTeamScore()) || chasing==0){
                            System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" All Out");
                            currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j+1)/10));
                            break outer;
                        }

                    }
                    else{
                        striker= team.getNextBatsman(wicket+1);
                    }
                }
                else {
                    System.out.println(printBall(i,j+1)+" "+score1 + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score1);
                    currentBowler.setRunsGiven(currentBowler.getRunsGiven()+score1);
                    totalScore+=score1;
                    if(score1==1 || score1==3){
                        Player temp = striker;
                        striker = nonStriker;
                        nonStriker = temp;
                    }
                    if (chasing==1 && totalScore > teamTwo.getTeamScore()) {
                        System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" Score:" +totalScore+"/"+wicket);
                        System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" won ");
                        win = 1;
                        break outer;
                    }
                }
            }
            currentBowler.setOversDone(currentBowler.getOversDone()+1);
            Player temp = striker;
            striker = nonStriker;
            nonStriker = temp;
        }
        if(chasing==1 && win==0) {
            System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" Score:"+totalScore+"/"+wicket);
            System.out.println("Team "+teamTwo.getTeamID()+" won by:" + (teamTwo.getTeamScore() - team.getTeamScore()));
        }
        team.setTeamScore(totalScore);
        team.setTotalWicketsFall(wicket);
    }
    public void matchSummary(Team team){
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
}
