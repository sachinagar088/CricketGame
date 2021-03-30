package com.sachin.CricketGame.Repository;


import com.sachin.CricketGame.models.Bowler;
import com.sachin.CricketGame.models.Player;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamPlayersRepository {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    BowlerRepository bowlerRepository;

    static Connection conn = DatabaseConnection.getConnection();

//    public void getTeamPlayers(int team_id) throws SQLException {
//        String sqlQuery = "select * from team_players where team_id = " + team_id;
//        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//        ResultSet rs = preparedStatement.executeQuery();
//        System.out.format("\n%30s\n", "Batting Summary");
//        System.out.format("\n%10s%10s%10s%10s\n", "Name", "Score", "Balls", "Out");
//        while (rs.next()) {
//            playerRepository.getPlayer(rs.getInt(2),team_id);
//        }
//        rs.close();
//        String sqlQuery1 = "select * from team_bowlers where team_id = " + team_id;
//        PreparedStatement preparedStatement1 = conn.prepareStatement(sqlQuery1);
//        ResultSet resultSet1 = preparedStatement1.executeQuery();
//        System.out.format("\n%30s\n", "Bowlers Summary");
//        System.out.format("\n%10s%10s%10s%10s\n", "Name", "Wickets", "Runs", "Overs");
//        while (resultSet1.next()) {
//            bowlerRepository.getBowler(resultSet1.getInt(2),team_id);
//        }
//        resultSet1.close();
//    }
    public List<Player> getTeamPlayersSummary(int team_id) {
        List<Player> players = new ArrayList<>();
        String sqlQuery = "select * from team_players where team_id = " + team_id;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                players.add(playerRepository.getPlayerSummary(rs.getInt(2), team_id));
            }
            rs.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return players;
    }

    public List<Bowler> getTeamBowlersSummary(int team_id) {
        String sqlQuery = "select * from team_bowlers where team_id = " + team_id;
        List<Bowler> bowlers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bowlers.add(bowlerRepository.getBowlerSummary(rs.getInt(2), team_id));
            }
            rs.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return bowlers;
    }
}
