package com.sachin.CricketGame.controller;

import com.sachin.CricketGame.models.*;
import com.sachin.CricketGame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameController{

    @Autowired
    MatchService matchService;

    @GetMapping("/match/{matchId}")
    public Cricket getMatchSummary(@PathVariable int matchId) {
        return matchService.getGameSummary(matchId);
    }

    @GetMapping("/match/{matchId}/play")
    public String playMatch(@PathVariable int matchId) {
        return matchService.startMatch(matchId);
    }

    @PostMapping("/match/{matchId}/innings/{inningsId}/over/play")
    public Innings playOver(@PathVariable int matchId , @PathVariable int inningsId,@RequestBody List<Integer> score){
        return matchService.startOver(matchId,inningsId,score);
    }

    @GetMapping("/match/{matchId}/overs")
    public List<Over> getOversInMatch(@PathVariable int matchId) {
        return matchService.getGameOverSummary(matchId);
    }

    @GetMapping("/match/{matchId}/players")
    public List<Player> getPlayersInMatch(@PathVariable int matchId) {
        return matchService.getGamePlayerSummary(matchId);
    }

    @GetMapping("/match/{matchId}/bowlers")
    public List<Bowler> getBowlersInMatch(@PathVariable int matchId) {
        return matchService.getGameBowlerSummary(matchId);
    }

    @GetMapping("/match/{matchId}/wickets")
    public List<Wicket> getWicketsInMatch(@PathVariable int matchId) {
        return matchService.getGameWicketSummary(matchId);
    }

    @PostMapping("/match/")
    public Cricket createMatch(@Valid @RequestBody Cricket cricket) {
        return matchService.addMatch(cricket);
    }

}
