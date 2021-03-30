package com.sachin.CricketGame.service;

import com.sachin.CricketGame.Repository.MatchRepository;
import com.sachin.CricketGame.Repository.TeamRepository;
import com.sachin.CricketGame.exception.CustomException;
import com.sachin.CricketGame.exception.RecordNotFound;
import com.sachin.CricketGame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MatchController matchController;
    @Autowired
    OverService overService;

    public String startMatch(int matchId) {
        Cricket cricket = matchRepository.getGameSummary(matchId);
        if(cricket == null)
            throw new RecordNotFound("Match ID doesn't exists");
        else if(cricket.getStatus().equals("completed"))
            throw new CustomException("Match Already Completed");
        else if(cricket.getStatus().equals("In_progress"))
            throw new CustomException("Match is already in progress");
        else {
            matchController.start(cricket);
            return "Match Started";
        }
    }
    public Innings startOver(int match_id,int innings_id,List<Integer> score){
        return overService.controlOver(innings_id,match_id,score);
    }
    public Cricket getGameSummary(int matchId) {
        Cricket cricket = matchRepository.getGameSummary(matchId);
        if(cricket == null){
            throw new RecordNotFound("Match Not Found");
        }
        return cricket;
    }

    public List<Over> getGameOverSummary(int matchId) {
        List<Over> overs = new ArrayList<>();
        Cricket cricket = getGameSummary(matchId);
        if (cricket != null) {
            if (cricket.getInnings_one() != null) {
                overs.addAll(cricket.getInnings_one().getOvers());
            }
            if (cricket.getInnings_two() != null) {
                overs.addAll(cricket.getInnings_two().getOvers());
            }
        }
        return overs;
    }
    public List<Player> getGamePlayerSummary(int matchId) {
        List<Player> players = new ArrayList<>();
        Cricket cricket = getGameSummary(matchId);
        if (cricket != null) {
            if (cricket.getTeamOne() != null) {
                players.addAll(cricket.getTeamOne().getPlayers());
            }
            if (cricket.getInnings_two() != null) {
                players.addAll(cricket.getTeamTwo().getPlayers());
            }
        }
        return players;
    }

    public List<Bowler> getGameBowlerSummary(int matchId) {
        List<Bowler> bowlers = new ArrayList<>();
        Cricket cricket = getGameSummary(matchId);
        if (cricket != null) {
            if (cricket.getTeamOne() != null) {
                bowlers.addAll(cricket.getTeamOne().getBowlers());
            }
            if (cricket.getInnings_two() != null) {
                bowlers.addAll(cricket.getTeamTwo().getBowlers());
            }
        }
        return bowlers;
    }

    public List<Wicket> getGameWicketSummary(int matchId) {
        List<Wicket> wickets = new ArrayList<>();
        Cricket cricket = getGameSummary(matchId);
        if (cricket != null) {
            if (cricket.getInnings_one()!= null) {
                wickets.addAll(cricket.getInnings_one().getWickets());
            }
            if (cricket.getInnings_two() != null) {
                wickets.addAll(cricket.getInnings_two().getWickets());
            }
        }
        return wickets;
    }

    public Cricket addMatch(Cricket cricket) {
        int teamOneSize = teamRepository.getTeamSummary(cricket.getTeamOne().getTeamID()).getPlayers().size();
        int teamTwoSize = teamRepository.getTeamSummary(cricket.getTeamTwo().getTeamID()).getPlayers().size();
        if(teamOneSize != 3){
            throw new CustomException("Team 1 size should be 3");
        }
        if(teamTwoSize != 3){
            throw new CustomException("Team 2 size should be 3");
        }
        int match_id = matchRepository.addMatch(cricket);
        return getGameSummary(match_id);
    }
}
