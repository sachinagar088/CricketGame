package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.exception.CustomException;
import com.sachin.CricketGame.models.Bowler;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BowlerRepository {
    static Connection conn = DatabaseConnection.getConnection();

    public void addBowler(Bowler bowler, int id) {
        String sqlQuery1 = "insert into team_bowlers values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement1 = conn.prepareStatement(sqlQuery1);
            preparedStatement1.setInt(1, id);
            preparedStatement1.setInt(2, bowler.getPlayerId());
            preparedStatement1.setInt(3, bowler.getWicketsTaken());
            preparedStatement1.setInt(4, bowler.getRunsGiven());
            preparedStatement1.setFloat(5, bowler.getOversDone());
            preparedStatement1.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void updateBowler(Bowler bowler,int team_id){
        String sqlQuery = "update team_bowlers set wickets = ?,runs = ?,overs = ? " +
                "where player_id  = " + bowler.getPlayerId() + " and team_id = " + team_id;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, bowler.getWicketsTaken());
            preparedStatement.setInt(2, bowler.getRunsGiven());
            preparedStatement.setFloat(3, bowler.getOversDone());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            throw new CustomException(sqlException.getMessage());
        }
    }

    public void getBowler(int id,int team_id) throws SQLException {
        String sqlQuery = "select player.Player_Name,bowler.wickets,bowler.runs,bowler.overs from Player player" +
                ",team_bowlers bowler where player.Player_Id = bowler.Player_Id and player.Player_Id = " + id + " and bowler.team_id = " + team_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            System.out.format("%10s%10d%10d%10.1f\n",rs.getString(1),rs.getInt(2),
                    rs.getInt(3),rs.getFloat(4));}
        rs.close();
    }
    public Bowler getBowlerSummary(int id,int team_id) {
        String sqlQuery = "select player.Player_Name,bowler.wickets,bowler.runs,bowler.overs,player.Player_role from Player player" +
                ",team_bowlers bowler where player.Player_Id = bowler.Player_Id and player.Player_Id = " + id + " and bowler.team_id = " + team_id;
        Bowler bowler = new Bowler();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                bowler.setPlayerId(id);
                bowler.setName(rs.getString(1));
                bowler.setPlayerRole(rs.getString(5));
                bowler.setWicketsTaken(rs.getInt(2));
                bowler.setRunsGiven(rs.getInt(3));
                bowler.setOversDone(rs.getFloat(4));
            }
            rs.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return bowler;
    }
}
