package com.sachin.CricketGame.service;

import com.sachin.CricketGame.Repository.*;
import com.sachin.CricketGame.exception.RecordNotFound;
import com.sachin.CricketGame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class InningsService {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    InningsRepository inningsRepository;
    @Autowired
    OverService overService;
    @Autowired
    PlayerService playerService;
    @Autowired
    OverRepository overRepository;
    @Autowired
    WicketRepository wicketRepository;
    @Autowired
    Transaction transaction;

//    public Innings startInnings(int matchId,int inningsId) throws SQLException {
//        Cricket cricket = matchRepository.getGameSummary(matchId);
//        if(cricket == null || (!cricket.getStatus().equals("In_progress"))){
//            return null;
//        }
//        if(inningsId == cricket.getInnings_one().getInnings_id()){
//            Innings firstInnings = new Innings(cricket.getInnings_one().getTeam(),cricket.getInnings_one().getTeamTwo(),0);
//            firstInnings.setInnings_id(inningsId);
//            inningsStarter.startInnings(firstInnings,cricket);
//            System.out.println("\nFirst Innings Over");
//            ScoreCard.teamSummary(firstInnings.getTeam(), firstInnings.getTeamTwo());
//            System.out.format("\n%30s\n","First Innings Summary");
//            ScoreCard.inningsSummary(firstInnings);
////            teamRepository.updateTeam(firstInnings.getTeam());
////            teamRepository.updateTeam(firstInnings.getTeamTwo());
//            inningsRepository.updateInningsSummary(firstInnings);
//        }
//        else{
//            Innings secondInnings = new Innings(cricket.getInnings_two().getTeam(),cricket.getInnings_two().getTeamTwo(),1);
//            secondInnings.setInnings_id(inningsId);
//            inningsStarter.startInnings(secondInnings,cricket);
//            System.out.println("\nSecond Innings Over");
//            ScoreCard.teamSummary(secondInnings.getTeam(), secondInnings.getTeamTwo());
//            System.out.format("\n%30s\n","Second Innings Summary");
//            ScoreCard.inningsSummary(secondInnings);
////            teamRepository.updateTeam(secondInnings.getTeam());
////            teamRepository.updateTeam(secondInnings.getTeamTwo());
//            updateTeamPlayers(cricket.getTeamOne().getPlayers());
//            updateTeamPlayers(cricket.getTeamTwo().getPlayers());
//            inningsRepository.updateInningsSummary(secondInnings);
//            cricket.setStatus("completed");
//            matchRepository.updateMatch(cricket,matchId);
//        }
//        return inningsRepository.getInningSummary(inningsId);
//    }

    public Innings startInnings(int matchId,int inningsId) {
        Cricket cricket = matchRepository.getGameSummary(matchId);
        if(cricket == null || (!cricket.getStatus().equals("In_progress"))){
            throw new RecordNotFound("Enter a Valid Match ID");
        }
        if(inningsId == cricket.getInnings_one().getInnings_id()){
            Innings firstInnings = new Innings(cricket.getInnings_one().getTeam(),cricket.getInnings_one().getTeamTwo(),0);
            firstInnings.setInnings_id(inningsId);
            transaction.startTransaction();
            for(int i=0;i<Cricket.getNoOfOvers();i++) {
                String allOut = overService.startOver(firstInnings, i, cricket);
                if(allOut.equals("allOut"))
                    break;
            }
            firstInnings.setOversDoneAndWicket(firstInnings.getTeam().getTotalWicketsFall());
            inningsRepository.updateInningsSummary(firstInnings);
            transaction.commitTransaction();
            System.out.println("\nFirst Innings Over");
            ScoreCard.teamSummary(firstInnings.getTeam(), firstInnings.getTeamTwo());
            System.out.format("\n%30s\n","First Innings Summary");
            ScoreCard.inningsSummary(firstInnings);
        }
        else{
            Innings secondInnings = new Innings(cricket.getInnings_two().getTeam(),cricket.getInnings_two().getTeamTwo(),1);
            secondInnings.setInnings_id(inningsId);
            String win = "";
            transaction.startTransaction();
            for(int i=0;i<Cricket.getNoOfOvers();i++){
                win = overService.startOver(secondInnings,i,cricket);
                if(win.equals("win") || win.equals("allOut"))
                    break;
            }
            if(win.equals("overCompleted") || win.equals("allOut")) {
                cricket.setWinning_team(secondInnings.getTeamTwo());
                cricket.setComments(MessageFormat.format("Won by {0} runs", secondInnings.getTeamTwo().getTeamScore() - secondInnings.getTeam().getTeamScore()));
            }
            secondInnings.setOversDoneAndWicket(secondInnings.getTeam().getTotalWicketsFall());
            inningsRepository.updateInningsSummary(secondInnings);
            System.out.println("\nSecond Innings Over");
            ScoreCard.teamSummary(secondInnings.getTeam(), secondInnings.getTeamTwo());
            System.out.format("\n%30s\n","Second Innings Summary");
            ScoreCard.inningsSummary(secondInnings);
            updateTeamPlayers(cricket.getTeamOne().getPlayers());
            updateTeamPlayers(cricket.getTeamTwo().getPlayers());
            cricket.setStatus("completed");
            matchRepository.updateMatch(cricket);
            transaction.commitTransaction();
        }
        return inningsRepository.getInningSummary(inningsId);
    }

    public void updateTeamPlayers(List<Player> players) {
        for(Player player : players){
            playerService.updatePlayer(player);
        }
    }
    public Innings getSummary(int innings_id) {
        return inningsRepository.getInningSummary(innings_id);
    }

    public List<Over> getOverSummary(int innings_id) {
        return overRepository.getOverSummary(innings_id);
    }

    public List<Wicket> getWicketSummary(int innings_id) {
        return wicketRepository.getWicketSummary(innings_id);
    }
}
