package com.sachin.CricketGame.service;

import com.sachin.CricketGame.Repository.TeamRepository;
import com.sachin.CricketGame.exception.RecordNotFound;
import com.sachin.CricketGame.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team getTeamSummary(int teamId) {
        Team team = teamRepository.getTeamSummary(teamId);
        if(team == null){
            throw new RecordNotFound("Team ID not found");
        }
        return team;
    }

    public Team addTeam(Team team) {
        return teamRepository.getTeamSummary(teamRepository.addTeam(team));
    }

    public Team updateTeam(Team team,int teamId){
        team.setTeamID(teamId);
        teamRepository.updateTeam(team);
        return getTeamSummary(team.getTeamID());
    }

}
