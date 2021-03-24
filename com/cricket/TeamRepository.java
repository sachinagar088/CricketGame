package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.models.Team;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class TeamRepository {

    @Autowired
    TeamPlayersRepository teamPlayersRepository;

    static Connection conn = DatabaseConnection.getConnection();

    public int addTeam(Team team) throws SQLException {
        int id = 0;
        String sqlQuery = "insert into team (team_name,team_score,team_wickets_fall) values (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, team.getTeamName());
        preparedStatement.setInt(2, team.getTeamScore());
        preparedStatement.setInt(3, team.getTotalWicketsFall());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next())
            id = rs.getInt(1);
        return id;
    }

    public void getTeam(int team_id) throws SQLException {

        String sqlQuery = "select * from team where team_id = " + team_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Team Name : " + resultSet.getString(2));
            System.out.println("Team Score : " + resultSet.getInt(3) + "/" + resultSet.getInt(4));
        }
        resultSet.close();
        teamPlayersRepository.getTeamPlayers(team_id);
    }
    public Team getTeamSummary(int team_id) throws SQLException {

        String sqlQuery = "select * from team where team_id = " + team_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        Team team = new Team();
        if (!resultSet.next()) {
            return null;
        }
        else {
              team.setTeamID(team_id);
              team.setTeamName(resultSet.getString(2));
              team.setTeamScore(resultSet.getInt(3));
              team.setTotalWicketsFall(resultSet.getInt(4));
              team.setPlayers(teamPlayersRepository.getTeamPlayersSummary(team_id));
              team.setBowlers(teamPlayersRepository.getTeamBowlersSummary(team_id));
        }
        resultSet.close();
        return team;
    }
    public void updateTeam(Team team) throws SQLException {
        String sqlQuery = "update team set team_score = ?,team_wickets_fall = ? where team_id  = " + team.getTeamID();
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, team.getTeamScore());
        preparedStatement.setInt(2, team.getTotalWicketsFall());
        preparedStatement.executeUpdate();
//        for (Player player : team.getPlayers()) {
//            playerRepository.updatePlayer(player,team.getTeamID());
//        }
//        for (Bowler bowler : team.getBowlers()) {
//            bowlerRepository.updateBowler(bowler,team.getTeamID());
//        }
    }
}
