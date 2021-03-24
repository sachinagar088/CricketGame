package com.sachin.CricketGame.models;

import com.sachin.CricketGame.Repository.BowlerRepository;
import com.sachin.CricketGame.Repository.PlayerRepository;
import com.sachin.CricketGame.Repository.TeamRepository;
import java.sql.SQLException;

public class TeamFactory {

    public Team getTeam() throws SQLException {
        Team team = new Team();
        Input input = new Input();
        TeamRepository teamDao = new TeamRepository();
        team.setTeamName(input.getString("Enter Team Name"));
        int id = teamDao.addTeam(team);
        team.setTeamID(id);
        addPlayer(team,input);
        return team;
    }
    public void addPlayer(Team team,Input input) throws SQLException{
        PlayerRepository playerDao =new PlayerRepository();
        BowlerRepository bowlerDao = new BowlerRepository();
        System.out.println("Enter Team "+team.getTeamName()+" Details \n");
        for(int i=0;i<3;i++){
            int player_id = input.getInteger("Enter the Player Id");
            Player player = playerDao.getPlayerDetails(player_id);
            playerDao.addPlayer(player, team.getTeamID());
            if(player.getPlayerRole().equals("Bowler")){
                Bowler bowler = new Bowler();
                bowler.setName(player.getName());
                bowler.setPlayerId(player.getPlayerId());
                bowlerDao.addBowler(bowler,team.getTeamID());
                team.addBowler(bowler);
            }
            team.addPlayer(player);
        }
        team.setTotalWicketsFall(0);
        team.setTeamScore(0);
    }
}
