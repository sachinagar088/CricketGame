package com.sachin.CricketGame.controller;

import com.sachin.CricketGame.models.Team;
import com.sachin.CricketGame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/team/{teamId}")
    public Team getTeamDetails(@PathVariable int teamId) {
        return teamService.getTeamSummary(teamId);
    }
    @PostMapping("/team/")
    public Team addTeamDetails(@Valid @RequestBody Team team) {
        return teamService.addTeam(team);
    }
    @PutMapping("/team/{teamId}")
    public Team updateTeamDetails(@Valid @RequestBody Team team,@PathVariable int teamId) {
        return teamService.updateTeam(team,teamId);
    }
}
