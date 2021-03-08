package com.cricket;

import java.util.List;

public class MatchMethod {
    public static MatchMethod getInstance(){
        return new MatchMethod();
    }
    public String printBall(int i,int j){
        return (i)+"."+(j);

    }
    public void playMatch(Cricket cricket,Team teamOne,Team teamTwo){
        startInnings(cricket,teamOne,teamTwo,0);
        cricket.setBattingFirstScore(teamOne.getTeamScore());
        System.out.println("Team "+teamOne.getTeamID()+" "+teamOne.getTeamName()+" scored "+teamOne.getTeamScore()+"/"+teamOne.getTotalWicketsFall());
        showBowlerSummary(teamTwo);
        System.out.println("First Innings Over");
        startInnings(cricket,teamTwo,teamOne,1);
        showBowlerSummary(teamOne);
    }
    public void startInnings(Cricket cricket,Team team,Team teamTwo,int chasing){
        int wicket=0;
        int totalScore=0;
        int win=0;
        int nextPlayer = 2;
        Player striker= team.getPlayers().get(0);
        Player nonStriker = team.getPlayers().get(1);
        outer:for(int i=0;i<cricket.getTotalOvers();i++){
            Bowler currentBowler = teamTwo.getBowler();
            System.out.println(currentBowler.getName()+" "+"is balling the "+i+1+" Over");
            for(int j=0;j<6;j++){
                int score1 = Util.randomNumbers();
                striker.setBallsTaken(striker.getBallsTaken()+1);
                if(score1==7){
                    wicket+=1;
                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                    if(wicket==cricket.getTotalWickets()){
                        if((chasing==1 && totalScore<cricket.getBattingFirstScore()) || chasing==0){
                            System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                            System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" All Out");
                            break outer;
                        }

                    }
                    else{
                        System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        striker= team.getPlayers().get(nextPlayer);
                        nextPlayer+=1;
                    }
                }
                else {
                    System.out.println(printBall(i,j+1)+" "+score1 + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score1);
                    currentBowler.setRunsGiven(currentBowler.getRunsGiven());
                    totalScore+=score1;
                    if(score1==1 || score1==3){
                        Player temp = striker;
                        striker = nonStriker;
                        nonStriker = temp;
                    }
                    if (chasing==1 && totalScore > cricket.getBattingFirstScore()) {
                        System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" Score:" +totalScore+"/"+wicket);
                        System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" won ");
                        win = 1;
                        break outer;
                    }
                }
            }
            Player temp = striker;
            striker = nonStriker;
            nonStriker = temp;
        }
        if(chasing==1 && win==0) {
            System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" Score:"+totalScore+"/"+wicket);
            System.out.println("Team "+cricket.getTeamBattingFirst()+" won by:" + (cricket.getBattingFirstScore() - totalScore));
        }
        team.setTeamScore(totalScore);
        team.setTotalWicketsFall(wicket);
    }
    /*public void battingFirst(Cricket c,Team t){
        int wicket=0;
        int allOut=0;
        int totalScore=0;
        int st=0;
        int nonSt=1;
        Player striker= t.getPlayers().get(st);
        Player nonStriker = t.getPlayers().get(nonSt);
        for(int i=0;i<c.getTotalOvers();i++){
            for(int j=0;j<6;j++){
                int score=Util.randomNumbers();
                striker.setBallsTaken(striker.getBallsTaken()+1);
                if(score==7){
                    wicket=wicket+1;
                    if(wicket==c.getTotalWickets()){
                        System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        System.out.println(format("Team {0} All Out", t.getTeamID()));
                        allOut=1;
                        break;
                    }
                    else{
                        System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        if(st>nonSt) {
                            st += 1;
                            striker = t.getPlayers().get(st);
                        }
                        else {
                            nonSt += 1;
                            striker = t.getPlayers().get(nonSt);
                        }
                    }
                }
                else {
                    System.out.println(printBall(i,j+1)+" "+score + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score);
                    totalScore+=score;
                    if(score==1 || score==3){
                        List<Player> sw = swap(striker,nonStriker);
                        striker = sw.get(0);
                        nonStriker = sw.get(1);
                    }
                }
            }
            if(allOut==1){
                break;
            }
            else{
                List<Player> sw = swap(striker,nonStriker);
                striker = sw.get(0);
                nonStriker = sw.get(1);
            }
        }
        t.setTeamScore(totalScore);
        t.setTotalWicketsFall(wicket);
        c.setBattingFirstScore(totalScore);
    }
    public void battingSecond(Cricket c,Team t){
        int wicket1=0;
        int totalScore1=0;
        int allOut=0;
        int win=0;
        int st=0;
        int nonSt=1;
        Player striker= t.getPlayers().get(st);
        Player nonStriker = t.getPlayers().get(nonSt);
        for(int i=0;i<c.getTotalOvers();i++){
            for(int j=0;j<6;j++){
                int score1 = Util.randomNumbers();
                striker.setBallsTaken(striker.getBallsTaken()+1);
                if(score1==7){
                    wicket1+=1;
                    if(wicket1==c.getTotalWickets()){
                        if(totalScore1<c.getBattingFirstScore()){
                            System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                            System.out.println("Team "+t.getTeamID()+" "+t.getTeamName()+" All Out");
                            allOut=1;
                            break;
                        }

                    }
                    else{
                        System.out.println(printBall(i,j+1)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        if(st>nonSt) {
                            st += 1;
                            striker = t.getPlayers().get(st);
                        }
                        else {
                            nonSt += 1;
                            striker = t.getPlayers().get(nonSt);
                        }
                    }
                }
                else {
                    System.out.println(printBall(i,j+1)+" "+score1 + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score1);
                    totalScore1+=score1;
                    if(score1==1 || score1==3){
                        List<Player> sw = swap(striker,nonStriker);
                        striker = sw.get(0);
                        nonStriker = sw.get(1);
                    }
                    if (totalScore1 > c.getBattingFirstScore()) {
                        System.out.println("Team "+t.getTeamID()+" "+t.getTeamName()+" Score:" +totalScore1+"/"+wicket1);
                        System.out.println("Team "+t.getTeamID()+" "+t.getTeamName()+" won ");
                        win = 1;
                        break;
                    }
                }
            }
            if(allOut==1 || win==1)
                break;
            else{
                List<Player> sw = swap(striker,nonStriker);
                striker = sw.get(0);
                nonStriker = sw.get(1);
            }
        }
        if(win==0) {
            System.out.println("Team "+t.getTeamID()+" "+t.getTeamName()+" Score:"+totalScore1+"/"+wicket1);
            System.out.println("Team "+c.getTeamBattingFirst()+" won by:" + (c.getBattingFirstScore() - totalScore1));
        }
        t.setTeamScore(totalScore1);
        t.setTotalWicketsFall(wicket1);
    }*/
    public void showBowlerSummary(Team team){
        List<Bowler> bowlers = team.getBowlers();
        for(Bowler bowler : bowlers){
            System.out.println(bowler.getName() + " has taken "+bowler.getWicketsTaken()+" Wickets");
        }
    }
    public void matchSummary(Team t){
        System.out.println("Team ID : "+ t.getTeamID());
        System.out.println("Team Name : "+t.getTeamName());
        System.out.format("%10s%10s%15s%10s","Name","Role","RunsScored","Balls");
        System.out.println();
        for(Player p : t.getPlayers()) {
            System.out.format("%12s%10s%10d%12d",p.getName(),p.getPlayerRole(),p.getRunsScored(),p.getBallsTaken());
            System.out.println();
        }
        System.out.println("Total Score : "+t.getTeamScore()+"/"+t.getTotalWicketsFall());

    }
}
