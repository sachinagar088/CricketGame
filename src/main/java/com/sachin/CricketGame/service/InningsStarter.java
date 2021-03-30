package com.sachin.CricketGame.service;

import com.sachin.CricketGame.Repository.*;
import com.sachin.CricketGame.models.*;
import com.sachin.CricketGame.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.MessageFormat;

@Service
public class InningsStarter {

    @Autowired
    WicketRepository wicketRepository;
    @Autowired
    BallRepository ballRepository;
    @Autowired
    OverRepository overRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    BowlerRepository bowlerRepository;
    @Autowired
    TeamRepository teamRepository;

    public boolean checkAllOut(Innings innings,int wicket){
        if(wicket == Cricket.getTotalWickets()){
            if((innings.getChasing()==1 && innings.getTeam().getTeamScore()<=innings.getTeamTwo().getTeamScore()) || innings.getChasing()==0){
                System.out.println("Team " + innings.getTeam().getTeamName()+ " All Out");
                return true;
            }

        }
        return false;
    }

    public float oversDone(float overs){
        float updateOvers = overs + (float) 0.1;
        if((updateOvers * 10) % 10 == 6.0){
            updateOvers = (float)((int)(updateOvers * 10) / 10) + (float) 1.0;
        }
        return updateOvers;
    }


    public void startInnings(Innings innings,Cricket cricket) throws SQLException {
        int wicket=0;
        int win=0;
        outer:for(int i=0;i<Cricket.getNoOfOvers();i++){
            Bowler currentBowler =innings.getTeamTwo().getBowler(i);
            Over over = new Over();
            over.setOverId(overRepository.addOver(innings.getInnings_id()));
            System.out.println("\n"+currentBowler.getName()+" "+"is balling the "+(i+1)+" Over\n");
            for(int j=1;j<=6;j++){
                int score = Util.randomNumbers(innings.getStriker().getPlayerRole());
                innings.getStriker().setBallsTaken(innings.getStriker().getBallsTaken()+1);
                currentBowler.setOversDone(oversDone(currentBowler.getOversDone()));
                Ball ball = new Ball(i+"."+j,score,innings.getStriker().getName(),currentBowler.getName());
                ballRepository.addBall(ball,over.getOverId());
                over.addBall(ball);
                if(score==7){
                    wicket+=1;
                    Wicket wicket_down = new Wicket(i+"."+j,innings.getStriker(),currentBowler);
                    wicketRepository.addWicket(wicket_down, innings.getInnings_id());
                    innings.getWickets().add(wicket_down);
                    innings.getStriker().setOutStatus(true);
                    innings.getStriker().calculateStrikeRate();
                    playerRepository.updatePlayer(innings.getStriker(),innings.getTeam().getTeamID());
                    System.out.println(printBall(i,j)+" WICKET!!!" + innings.getStriker().getName()+" is Out at "+innings.getStriker().getRunsScored());
                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                    bowlerRepository.updateBowler(currentBowler,innings.getTeamTwo().getTeamID());
                    over.setWicketsTaken(over.getWicketsTaken()+1);
                    overRepository.updateOver(over);
                    innings.getTeam().setTotalWicketsFall(wicket);
                    teamRepository.updateTeam(innings.getTeam());
                    if(checkAllOut(innings,wicket)){
                        //currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
                        innings.getOvers().add(over);
                        break outer;
                    }
                    else{
                        innings.setStriker(innings.getTeam().getNextBatsman(wicket+1));
                    }
                }
                else {
                    System.out.println(printBall(i,j)+" "+score + " run " +" hit by " + innings.getStriker().getName());
                    innings.getStriker().setRunsScored(innings.getStriker().getRunsScored()+score);
                    innings.getStriker().calculateStrikeRate();
                    playerRepository.updatePlayer(innings.getStriker(),innings.getTeam().getTeamID());
                    currentBowler.setRunsGiven(currentBowler.getRunsGiven()+score);
                    bowlerRepository.updateBowler(currentBowler,innings.getTeamTwo().getTeamID());
                    over.setRunsScored(over.getRunsScored()+score);
                    overRepository.updateOver(over);
                    innings.getTeam().setTeamScore(innings.getTeam().getTeamScore()+score);
                    teamRepository.updateTeam(innings.getTeam());
                    if(score==1 || score==3){
                        innings.swapPlayers();
                    }
                    if (innings.getChasing()==1 && innings.getTeam().getTeamScore() > innings.getTeamTwo().getTeamScore()) {
                        //currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
                        innings.getOvers().add(over);
                        cricket.setWinning_team(innings.getTeam());
                        cricket.setComments(MessageFormat.format("Won by {0} wickets", Cricket.getTotalWickets() - wicket));
                        win = 1;
                        break outer;
                    }
                }

            }
            //currentBowler.setOversDone(currentBowler.getOversDone()+1);
            innings.getOvers().add(over);
            innings.swapPlayers();
        }
        if(innings.getChasing()==1 && win==0) {
            cricket.setWinning_team(innings.getTeamTwo());
            cricket.setComments(MessageFormat.format("Won by {0} runs", innings.getTeamTwo().getTeamScore() - innings.getTeam().getTeamScore()));
        }
        //team.setTotalWicketsFall(wicket);
        innings.setOversDoneAndWicket(wicket);
    }
    public String printBall(int i,int j){
        return (i)+"."+(j);

    }
}
