package com.sachin.CricketGame.controller;

import com.sachin.CricketGame.models.Player;
import com.sachin.CricketGame.models.Team;
import com.sachin.CricketGame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/player/{playerId}")
    public Player getPLayer(@PathVariable int playerId) {
        return playerService.getPlayerDetail(playerId);
    }

    @GetMapping("/match/{matchId}/player/{playerId}")
    public Player getPlayerInMatch(@PathVariable int matchId,@PathVariable int playerId) {
        return playerService.getPlayerDetailInMatch(playerId,matchId);
    }

    @PostMapping("/team/{teamId}/player/{playerId}")
    public Team addPlayerInTeam(@PathVariable int teamId, @PathVariable int playerId) {
        return playerService.addPlayer(playerId, teamId);
    }

    @PostMapping("/player/")
    public Player addPlayerDetail(@Valid @RequestBody Player player) {
        return playerService.addPlayerBasicDetail(player);
    }

}
