package com.cricket;

import java.util.ArrayList;
import java.util.List;

public class Innings{
    private float oversDone;
    private int wicket;
    private List<Over> overs;
    private List<Wicket> wickets;
    private Team team;
    private Team teamTwo;
    private int chasing;
    private Player striker;
    private Player nonStriker;

    public Innings(Team team,Team teamTwo,int chasing){
        this.team=team;
        this.teamTwo=teamTwo;
        this.chasing=chasing;
        this.striker = team.getPlayers().get(0);
        this.nonStriker=team.getPlayers().get(1);
        this.overs=new ArrayList<>();
        this.wickets = new ArrayList<>();
    }

    public void swapPlayers(){
        Player temp=striker;
        striker=nonStriker;
        nonStriker=temp;
    }
    public void setOversDoneAndWicket(int wicket1){
        float sum = 0.0F;
        for(Bowler bowler : teamTwo.getBowlers()) {
            sum+=bowler.getOversDone();
        }
        oversDone = sum;
        wicket = wicket1;
    }
    public float getOversDone(){
        return oversDone;
    }

    public List<Over> getOvers() {
        return overs;
    }

    public int getWicket() {
        return wicket;
    }

    public List<Wicket> getWickets() {
        return wickets;
    }

    public boolean checkAllOut(int wicket){
        if(wicket==Cricket.getTotalWickets()){
            if((chasing==1 && team.getTeamScore()<=teamTwo.getTeamScore()) || chasing==0){
                System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" All Out");
                return true;
            }

        }
        return false;
    }
    public void startInnings(){
        int wicket=0;
        int win=0;
        outer:for(int i=0;i<Cricket.getNoOfOvers();i++){
            Bowler currentBowler = teamTwo.getBowler(i);
            Over over =Over.getInstance();
            System.out.println(currentBowler.getName()+" "+"is balling the "+(i+1)+" Over");
            for(int j=1;j<=6;j++){
                int score = Util.randomNumbers(striker.getPlayerRole());
                striker.setBallsTaken(striker.getBallsTaken()+1);
                Ball ball =Ball.getInstance(i+"."+j,score,striker.getName(),currentBowler.getName());
                over.addBall(ball);
                if(score==7){
                    wicket+=1;
                    Wicket wicket_down = new Wicket(i+"."+j,striker,currentBowler);
                    wickets.add(wicket_down);
                    striker.setOutStatus(true);
                    System.out.println(printBall(i,j)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                    over.setWicketsTaken(over.getWicketsTaken()+1);
                    if(checkAllOut(wicket)){
                        currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
                        overs.add(over);
                        break outer;
                    }
                    else{
                        striker= team.getNextBatsman(wicket+1);
                    }
                }
                else {
                    System.out.println(printBall(i,j)+" "+score + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score);
                    currentBowler.setRunsGiven(currentBowler.getRunsGiven()+score);
                    over.setRunsScored(over.getRunsScored()+score);
                    team.setTeamScore(team.getTeamScore()+score);
                    if(score==1 || score==3){
                        swapPlayers();
                    }
                    if (chasing==1 && team.getTeamScore() > teamTwo.getTeamScore()) {
                        System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" Score:" +team.getTeamScore()+"/"+wicket);
                        System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" won ");
                        currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
                        overs.add(over);
                        win = 1;
                        break outer;
                    }
                }
            }
            currentBowler.setOversDone(currentBowler.getOversDone()+1);
            overs.add(over);
            swapPlayers();
        }
        if(chasing==1 && win==0) {
            System.out.println("Team "+team.getTeamID()+" "+team.getTeamName()+" Score:"+team.getTeamScore()+"/"+wicket);
            System.out.println("Team "+teamTwo.getTeamID()+" won by:" + (teamTwo.getTeamScore() - team.getTeamScore()));
        }
        team.setTotalWicketsFall(wicket);
        setOversDoneAndWicket(wicket);
    }
    public String printBall(int i,int j){
        return (i)+"."+(j);

    }
}
