package com.sachin.CricketGame.Repository;


import com.sachin.CricketGame.exception.CustomException;
import com.sachin.CricketGame.models.Wicket;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WicketRepository {
    static Connection conn
            = DatabaseConnection.getConnection();

    public void addWicket(Wicket wicket, int id) {
        String sqlQuery = "insert into wicket values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, wicket.getBall());
            preparedStatement.setInt(2, wicket.getBatsman().getPlayerId());
            preparedStatement.setInt(3, wicket.getBowler().getPlayerId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            //exception.printStackTrace();
            throw new CustomException(exception.getMessage());
        }
    }

    public void getWicket(int innings_id) {
        String sqlQuery = "select ball,batsman.Player_Name,bowler.Player_Name from wicket " +
                "inner join Player as batsman on batsman_id = batsman.Player_Id " +
                "inner join Player as bowler on bowler_id = bowler.Player_Id and innings_id = " + innings_id;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.format("\n%10s%15s%10s\n", "Ball", "Batsman Out", "Bowler");
            while (rs.next()) {
                System.out.format("%10s%15s%10s\n", rs.getString(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
    public List<Wicket> getWicketSummary(int innings_id){
        String sqlQuery = "select ball,batsman.Player_Name,bowler.Player_Name from wicket " +
                "inner join Player as batsman on batsman_id = batsman.Player_Id " +
                "inner join Player as bowler on bowler_id = bowler.Player_Id and innings_id = " + innings_id;
        List<Wicket> wickets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Wicket wicket = new Wicket(rs.getString(1), rs.getString(2), rs.getString(3));
                wickets.add(wicket);
            }
            rs.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return wickets;
    }

}
