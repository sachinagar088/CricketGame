package com.sachin.CricketGame.service;

import com.sachin.CricketGame.Repository.BowlerRepository;
import com.sachin.CricketGame.Repository.PlayerRepository;
import com.sachin.CricketGame.Repository.TeamRepository;
import com.sachin.CricketGame.exception.CustomException;
import com.sachin.CricketGame.exception.RecordNotFound;
import com.sachin.CricketGame.models.Bowler;
import com.sachin.CricketGame.models.Player;
import com.sachin.CricketGame.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    BowlerRepository bowlerRepository;
    @Autowired
    TeamRepository teamRepository;

    public Team addPlayer(int player_id, int team_id) {
        if(teamRepository.getTeamSummary(team_id).getPlayers().size()==3){
            throw new CustomException("Maximum Player added");
        }
        Player player = playerRepository.getPlayerDetails(player_id);
        if(player == null){
            throw new RecordNotFound("Player not found");
        }
        playerRepository.addPlayer(player,team_id);
        if(player.getPlayerRole().equals("Bowler")){
            Bowler bowler = new Bowler();
            bowler.setPlayerId(player.getPlayerId());
            bowler.setName(player.getName());
            bowlerRepository.addBowler(bowler,team_id);
        }
        return teamRepository.getTeamSummary(team_id);
    }

    public Player addPlayerBasicDetail(Player player){
        return playerRepository.addIndividualPlayer(player);
    }

    public void updatePlayer(Player player) {
        player.setAvgRuns(((player.getAvgRuns()*player.getNoOfMatches())+player.getRunsScored()) / (player.getNoOfMatches()+1));
        player.setAvgStrikeRate(((player.getAvgStrikeRate()*player.getNoOfMatches())+player.getStrikeRate()) / (player.getNoOfMatches()+1));
        player.setNoOfMatches(player.getNoOfMatches()+1);
        playerRepository.updatePlayer(player);
    }

    public Player getPlayerDetailInMatch(int player_id,int match_id) {
        Player player = playerRepository.getPlayerInMatch(match_id,player_id);
        if (player == null)
            throw new RecordNotFound("Player not found");
        return player;
    }

    public Player getPlayerDetail(int player_id) {
        Player player = playerRepository.getPlayerDetails(player_id);
        if(player == null)
            throw new RecordNotFound("Player ID doesn't exist");
        return player;
    }
}
