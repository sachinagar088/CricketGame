package com.sachin.CricketGame.service;
//
//import com.sachin.CricketGame.Repository.CurrentStatusRepository;
//import com.sachin.CricketGame.Repository.MatchRepository;
//import com.sachin.CricketGame.exception.CustomException;
//import com.sachin.CricketGame.exception.RecordNotFound;
//import com.sachin.CricketGame.models.Cricket;
//import com.sachin.CricketGame.models.Innings;
//import com.sachin.CricketGame.models.Over;
//import com.sachin.CricketGame.models.Player;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.sql.SQLException;
//import java.text.MessageFormat;
//import java.util.List;
//
//public class OverService {
//
//    @Autowired
//    MatchRepository matchRepository;
//    @Autowired
//    OverStarter overStarter;
//    @Autowired
//    CurrentStatusRepository currentStatusRepository;
//
////    public void startOver(int matchId, int innings_id) throws SQLException {
////        Cricket cricket = matchRepository.getGameSummary(matchId);
////        if (cricket == null) {
////            throw new CustomException("Enter a Valid Match ID");
////        }
////        if (!cricket.getStatus().equals("In_progress")) {
////            throw new CustomException("Match has already been Completed");
////        }
////        if (cricket.getInnings_one().getInnings_id() == innings_id) {
////            if (cricket.getInnings_one().getOversDone() != Cricket.getNoOfOvers()) {
////                if (cricket.getInnings_one().getWicket() != Cricket.getTotalWickets()) {
////                    Innings innings = cricket.getInnings_one();
////                    innings.setChasing(0);
////                    List<Integer> playerId = currentStatusRepository.getCurrentStatus();
////                    innings.setStriker(((Player) innings.getTeam().getPlayers().stream().filter(player -> player.getPlayerId() == playerId.get(0))));
////                    innings.setNonStriker(((Player) innings.getTeam().getPlayers().stream().filter(player -> player.getPlayerId() == playerId.get(1))));
////                    Over over = overStarter.startOver(innings,cricket);
////                } else {
////                    throw new CustomException("Cannot start the over Team All Out");
////                }
////            } else {
////                throw new CustomException("Overs Completed");
////            }
////        }else if (cricket.getInnings_two().getInnings_id() == innings_id) {
////            if (cricket.getInnings_two().getOversDone() != Cricket.getNoOfOvers()) {
////                Innings innings = cricket.getInnings_one();
////                innings.setChasing(0);
////                List<Integer> playerId = currentStatusRepository.getCurrentStatus();
////                innings.setStriker(((Player) innings.getTeam().getPlayers().stream().filter(player -> player.getPlayerId() == playerId.get(0))));
////                innings.setNonStriker(((Player) innings.getTeam().getPlayers().stream().filter(player -> player.getPlayerId() == playerId.get(1))));
////                Over over = overStarter.startOver(cricket.getInnings_two(), cricket);
////                if (over == null) {
////                    //match won
////                } else {
////                    cricket.setWinning_team(cricket.getInnings_two().getTeamTwo());
////                    cricket.setComments(MessageFormat.format("Won by {0} runs", cricket.getInnings_two().getTeamTwo().getTeamScore() - cricket.getInnings_two().getTeam().getTeamScore()));
////                }
////            } else {
////                throw new CustomException("Overs Completed");
////            }
////        } else {
////            throw new RecordNotFound("Enter a Valid Innings ID");
////        }
////    }
//}

import com.sachin.CricketGame.Repository.InningsRepository;
import com.sachin.CricketGame.Repository.MatchRepository;
import com.sachin.CricketGame.Repository.OverRepository;
import com.sachin.CricketGame.Repository.Transaction;
import com.sachin.CricketGame.exception.CustomException;
import com.sachin.CricketGame.exception.RecordNotFound;
import com.sachin.CricketGame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class OverService {

    @Autowired
    InningsRepository inningsRepository;
    @Autowired
    InningsService inningsService;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    OverRepository overRepository;
    @Autowired
    BallService ballService;
    @Autowired
    Transaction transaction;

    public boolean checkAllOut(Innings innings){
        if(innings.getTeam().getTotalWicketsFall() == Cricket.getTotalWickets()){
            if((innings.getChasing()==1 && innings.getTeam().getTeamScore()<=innings.getTeamTwo().getTeamScore()) || innings.getChasing()==0){
                System.out.println("Team " + innings.getTeam().getTeamName()+ " All Out");
                return true;
            }

        }
        return false;
    }

    public String startOver(Innings innings,int overNumber,Cricket cricket){
        Bowler currentBowler = innings.getTeamTwo().getBowler(overNumber);
        Over over = new Over();
        over.setOverId(overRepository.addOver(innings.getInnings_id()));
        System.out.println("\n"+currentBowler.getName()+" "+"is balling the "+(overNumber+1)+" Over\n");
        for(int j = 1 ; j <= 6 ; j++){
            //to be corrected
            Ball ball = ballService.playBall(currentBowler,innings.getStriker(),over, innings.getTeam(), innings.getTeamTwo(),overNumber,j, innings.getInnings_id(),j);
            over.addBall(ball);
            if(ball.getRunsScored() == 7){
                Wicket wicket_down = new Wicket(overNumber + "." + j, innings.getStriker(), currentBowler);
                innings.getWickets().add(wicket_down);
                if (checkAllOut(innings)) {
                    innings.getOvers().add(over);
                    return "allOut";
                }
                else {
                    innings.setStriker(innings.getTeam().getNextBatsman(innings.getTeam().getTotalWicketsFall() + 1));
                }
            }
            if( ball.getRunsScored() == 1 || ball.getRunsScored() == 3){
                innings.swapPlayers();
            }
            if (innings.getChasing() == 1 && innings.getTeam().getTeamScore() > innings.getTeamTwo().getTeamScore()) {
                innings.getOvers().add(over);
                cricket.setWinning_team(innings.getTeam());
                cricket.setComments(MessageFormat.format("Won by {0} wickets", Cricket.getTotalWickets() - innings.getTeam().getTotalWicketsFall()));
                return "win";
            }
        }
        innings.getOvers().add(over);
        innings.swapPlayers();
        return "overCompleted";
    }

    public Innings controlOver(int innings_id, int match_id, List<Integer> score){
        Cricket cricket = matchRepository.getGameSummary(match_id);
        if(cricket == null || (!cricket.getStatus().equals("In_progress"))){
            throw new RecordNotFound("Enter a Valid Match ID");
        }
        Innings innings = inningsRepository.getInningSummary(innings_id);
        if(innings.getOversDone() == Cricket.getNoOfOvers()){
            throw new CustomException("Overs Completed for the innings");
        }
        if(innings.getWicket() == Cricket.getTotalWickets()){
            throw new CustomException("Team " + innings.getTeam().getTeamName()+" All out");
        }
        int overNumber = (int) innings.getOversDone();
        Bowler currentBowler = innings.getTeamTwo().getBowler(overNumber);
        transaction.startTransaction();
        Over over = new Over();
        over.setOverId(overRepository.addOver(innings.getInnings_id()));
        System.out.println("\n"+currentBowler.getName()+" "+"is balling the "+(overNumber+1)+" Over\n");
        for(int j = 1 ; j <= 6 ; j++){
            Ball ball = ballService.playBall(currentBowler,innings.getStriker(),over, innings.getTeam(), innings.getTeamTwo(),overNumber,j, innings.getInnings_id(),score.get(j-1));
            over.addBall(ball);
            if(ball.getRunsScored() == 7){
                Wicket wicket_down = new Wicket(overNumber + "." + j, innings.getStriker(), currentBowler);
                innings.getWickets().add(wicket_down);
                if (checkAllOut(innings)) {
                    innings.getOvers().add(over);
                    break;
                }
                else {
                    innings.setStriker(innings.getTeam().getNextBatsman(innings.getTeam().getTotalWicketsFall() + 1));
                }
            }
            if( ball.getRunsScored() == 1 || ball.getRunsScored() == 3){
                innings.swapPlayers();
            }
            if (innings.getChasing() == 1 && innings.getTeam().getTeamScore() > innings.getTeamTwo().getTeamScore()) {
                innings.getOvers().add(over);
                cricket.setStatus("completed");
                cricket.setWinning_team(innings.getTeam());
                cricket.setComments(MessageFormat.format("Won by {0} wickets", Cricket.getTotalWickets() - innings.getTeam().getTotalWicketsFall()));
                matchRepository.updateMatch(cricket);
//                inningsService.updateTeamPlayers(innings.getTeam().getPlayers());
//                inningsService.updateTeamPlayers(innings.getTeamTwo().getPlayers());
                break;
            }
        }
        innings.getOvers().add(over);
        innings.swapPlayers();
        innings.setOversDoneAndWicket(innings.getTeam().getTotalWicketsFall());
        inningsRepository.updateInningsSummary(innings);
        transaction.commitTransaction();
        return inningsRepository.getInningSummary(innings_id);
    }

}
