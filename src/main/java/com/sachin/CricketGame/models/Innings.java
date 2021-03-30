package com.sachin.CricketGame.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Innings{
    private int innings_id;
    private float oversDone;
    private int wicket;
    private List<Over> overs;
    private List<Wicket> wickets;
    private Team team;
    private Team teamTwo;
    @JsonIgnore
    private int chasing;
    @JsonIgnore
    private Player striker;
    @JsonIgnore
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
    public Innings(float oversDone,int wicket,List<Wicket> wickets,List<Over> overs,Team team,Team teamTwo){
        this.oversDone = oversDone;
        this.wicket = wicket;
        this.wickets = wickets;
        this.overs = overs;
        this.team = team;
        this.teamTwo = teamTwo;
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

    public void setOversDone(float oversDone) {
        this.oversDone = oversDone;
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

    public Team getTeam() {
        return team;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public int getInnings_id() {
        return innings_id;
    }

    public void setInnings_id(int innings_id) {
        this.innings_id = innings_id;
    }

    public int getChasing() {
        return chasing;
    }

    public void setChasing(int chasing) {
        this.chasing = chasing;
    }

    public Player getStriker() {
        return striker;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }
    //    public boolean checkAllOut(int wicket){
//        if(wicket==Cricket.getTotalWickets()){
//            if((chasing==1 && team.getTeamScore()<=teamTwo.getTeamScore()) || chasing==0){
//                System.out.println("Team " + team.getTeamName()+ " All Out");
//                return true;
//            }
//
//        }
//        return false;
//    }

//    public float oversDone(float overs){
//        float updateOvers = overs + (float) 0.1;
//        if((updateOvers * 10) % 10 == 6.0){
//            updateOvers = ((updateOvers * 10) / 10) + (float) 1.0;
//        }
//        return updateOvers;
//    }

//    public void startInnings(Cricket cricket) throws SQLException {
//        int wicket=0;
//        int win=0;
//        outer:for(int i=0;i<Cricket.getNoOfOvers();i++){
//            Bowler currentBowler = teamTwo.getBowler(i);
//            Over over = new Over();
//            over.setOverId(overRepository.addOver(innings_id));
//            System.out.println("\n"+currentBowler.getName()+" "+"is balling the "+(i+1)+" Over\n");
//            for(int j=1;j<=6;j++){
//                int score = Util.randomNumbers(striker.getPlayerRole());
//                striker.setBallsTaken(striker.getBallsTaken()+1);
//                currentBowler.setOversDone(oversDone(currentBowler.getOversDone()));
//                Ball ball =Ball.getInstance(i+"."+j,score,striker.getName(),currentBowler.getName());
//                ballRepository.addBall(ball,over.getOverId());
//                over.addBall(ball);
//                if(score==7){
//                    wicket+=1;
//                    Wicket wicket_down = new Wicket(i+"."+j,striker,currentBowler);
//                    wicketRepository.addWicket(wicket_down,innings_id);
//                    wickets.add(wicket_down);
//                    striker.setOutStatus(true);
//                    striker.calculateStrikeRate();
//                    playerRepository.updatePlayer(striker,team.getTeamID());
//                    System.out.println(printBall(i,j)+" WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
//                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
//                    bowlerRepository.updateBowler(currentBowler,teamTwo.getTeamID());
//                    over.setWicketsTaken(over.getWicketsTaken()+1);
//                    overRepository.updateOver(over);
//                    team.setTotalWicketsFall(wicket);
//                    teamRepository.updateTeam(team);
//                    if(checkAllOut(wicket)){
//                        //currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
//                        overs.add(over);
//                        break outer;
//                    }
//                    else{
//                        striker= team.getNextBatsman(wicket+1);
//                    }
//                }
//                else {
//                    System.out.println(printBall(i,j)+" "+score + " run " +" hit by " + striker.getName());
//                    striker.setRunsScored(striker.getRunsScored()+score);
//                    striker.calculateStrikeRate();
//                    playerRepository.updatePlayer(striker,team.getTeamID());
//                    currentBowler.setRunsGiven(currentBowler.getRunsGiven()+score);
//                    bowlerRepository.updateBowler(currentBowler,teamTwo.getTeamID());
//                    over.setRunsScored(over.getRunsScored()+score);
//                    overRepository.updateOver(over);
//                    team.setTeamScore(team.getTeamScore()+score);
//                    teamRepository.updateTeam(team);
//                    if(score==1 || score==3){
//                        swapPlayers();
//                    }
//                    if (chasing==1 && team.getTeamScore() > teamTwo.getTeamScore()) {
//                        //currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
//                        overs.add(over);
//                        cricket.setWinning_team(team);
//                        cricket.setComments(MessageFormat.format("Won by {0} wickets", Cricket.getTotalWickets() - wicket));
//                        win = 1;
//                        break outer;
//                    }
//                }
//
//            }
//            //currentBowler.setOversDone(currentBowler.getOversDone()+1);
//            overs.add(over);
//            swapPlayers();
//        }
//        if(chasing==1 && win==0) {
//            cricket.setWinning_team(teamTwo);
//            cricket.setComments(MessageFormat.format("Won by {0} runs", teamTwo.getTeamScore() - team.getTeamScore()));
//        }
//        //team.setTotalWicketsFall(wicket);
//        setOversDoneAndWicket(wicket);
//    }
//    public String printBall(int i,int j){
//        return (i)+"."+(j);
//
//    }
}
