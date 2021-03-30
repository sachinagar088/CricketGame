//package com.sachin.CricketGame.service;
//
//import com.sachin.CricketGame.Repository.*;
//import com.sachin.CricketGame.models.*;
//import com.sachin.CricketGame.util.Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.text.MessageFormat;
//
//@Service
//public class OverStarter {
//
//    @Autowired
//    MatchRepository matchRepository;
//    @Autowired
//    InningsRepository inningsRepository;
//    @Autowired
//    WicketRepository wicketRepository;
//    @Autowired
//    BallRepository ballRepository;
//    @Autowired
//    OverRepository overRepository;
//    @Autowired
//    PlayerRepository playerRepository;
//    @Autowired
//    BowlerRepository bowlerRepository;
//    @Autowired
//    TeamRepository teamRepository;
//    @Autowired
//    InningsStarter inningsStarter;
//    @Autowired
//    CurrentStatusRepository currentStatusRepository;
//
//    public float oversDone(float overs){
//        float updateOvers = overs + (float) 0.1;
//        if((updateOvers * 10) % 10 == 6.0){
//            updateOvers = (float)((int)(updateOvers * 10) / 10) + (float) 1.0;
//        }
//        return updateOvers;
//    }
//
//    public Over startOver(Innings innings,Cricket cricket) throws SQLException {
//        int i = (int)innings.getOversDone();
//        Bowler currentBowler =innings.getTeamTwo().getBowler(i);
//        Over over = new Over();
//        over.setOverId(overRepository.addOver(innings.getInnings_id()));
//        System.out.println("\n"+currentBowler.getName()+" "+"is balling the "+(i+1)+" Over\n");
//        for(int j=1;j<=6;j++){
//            int score = Util.randomNumbers(innings.getStriker().getPlayerRole());
//                innings.getStriker().setBallsTaken(innings.getStriker().getBallsTaken()+1);
//                innings.setOversDone(oversDone(innings.getOversDone()));
//                currentBowler.setOversDone(oversDone(currentBowler.getOversDone()));
//                Ball ball =Ball.getInstance(i+"."+j,score,innings.getStriker().getName(),currentBowler.getName());
//                ballRepository.addBall(ball,over.getOverId());
//                over.addBall(ball);
//                if(score==7){
//                    Wicket wicket_down = new Wicket(i+"."+j,innings.getStriker(),currentBowler);
//                    wicketRepository.addWicket(wicket_down, innings.getInnings_id());
//                    innings.getWickets().add(wicket_down);
//                    innings.getStriker().setOutStatus(true);
//                    innings.getStriker().calculateStrikeRate();
//                    playerRepository.updatePlayer(innings.getStriker(),innings.getTeam().getTeamID());
//                    System.out.println(printBall(i,j)+" WICKET!!!" + innings.getStriker().getName()+" is Out at "+innings.getStriker().getRunsScored());
//                    currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
//                    bowlerRepository.updateBowler(currentBowler,innings.getTeamTwo().getTeamID());
//                    over.setWicketsTaken(over.getWicketsTaken()+1);
//                    overRepository.updateOver(over);
//                    innings.getTeam().setTotalWicketsFall(innings.getTeam().getTotalWicketsFall()+1);
//                    teamRepository.updateTeam(innings.getTeam());
//                    innings.setWicket(innings.getWicket()+1);
//                    inningsRepository.updateInningsSummary(innings);
//                    if(inningsStarter.checkAllOut(innings)){
//                        //currentBowler.setOversDone(currentBowler.getOversDone()+((float)(j)/10));
//                        innings.getOvers().add(over);
//                        return null;
//                    }
//                    else{
//                        innings.setStriker(innings.getTeam().getNextBatsman(innings.getWicket()+1));
//                    }
//                }
//                else {
//                    System.out.println(printBall(i,j)+" "+score + " run " +" hit by " + innings.getStriker().getName());
//                    innings.getStriker().setRunsScored(innings.getStriker().getRunsScored()+score);
//                    innings.getStriker().calculateStrikeRate();
//                    playerRepository.updatePlayer(innings.getStriker(),innings.getTeam().getTeamID());
//                    currentBowler.setRunsGiven(currentBowler.getRunsGiven()+score);
//                    bowlerRepository.updateBowler(currentBowler,innings.getTeamTwo().getTeamID());
//                    over.setRunsScored(over.getRunsScored()+score);
//                    overRepository.updateOver(over);
//                    innings.getTeam().setTeamScore(innings.getTeam().getTeamScore()+score);
//                    teamRepository.updateTeam(innings.getTeam());
//                    inningsRepository.updateInningsSummary(innings);
//                    if(score==1 || score==3){
//                        innings.swapPlayers();
//                    }
//                    if (innings.getChasing()==1 && innings.getTeam().getTeamScore() > innings.getTeamTwo().getTeamScore()) {
//                        innings.getOvers().add(over);
//                        cricket.setWinning_team(innings.getTeam());
//                        cricket.setComments(MessageFormat.format("Won by {0} wickets", Cricket.getTotalWickets() - innings.getWicket()));
//                        matchRepository.updateMatch(cricket, cricket.getMatch_id());
//                        //win = 1;
//                        return null;
//                    }
//                }
//            }
//            innings.getOvers().add(over);
//            innings.swapPlayers();
//            currentStatusRepository.updateCurrentStatus(innings.getStriker().getPlayerId(),innings.getNonStriker().getPlayerId());
//            return over;
//        }
//    public String printBall(int i,int j){
//        return (i)+"."+(j);
//
//    }
//}
