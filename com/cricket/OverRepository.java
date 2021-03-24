package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.models.Over;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OverRepository {

    @Autowired
    BallRepository ballRepository;

    static Connection conn = DatabaseConnection.getConnection();

    public int addOver(int innings_id) throws SQLException {
        int over_id = 0;
        String sqlQuery = "insert into overs (runs,wickets,innings_id) values (0,0,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, innings_id);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            over_id = rs.getInt(1);
        }
        return over_id;
    }
    public void updateOver(Over over) throws SQLException {
        String sqlQuery = "update overs set runs = ?,wickets = ? where over_id = " + over.getOverId();
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setInt(1,over.getRunsScored());
        preparedStatement.setInt(2,over.getWicketsTaken());
        preparedStatement.executeUpdate();
    }
//    public void addOver(Over over, int innings_id) throws SQLException {
//        String sqlQuery = "insert into overs (runs,wickets,innings_id) values (?,?,?)";
//        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.setInt(1, over.getRunsScored());
//        preparedStatement.setInt(2, over.getWicketsTaken());
//        preparedStatement.setInt(3, innings_id);
//        preparedStatement.executeUpdate();
//        ResultSet rs = preparedStatement.getGeneratedKeys();
//        if (rs.next()) {
//            int over_id = rs.getInt(1);
//            List<Ball> balls = over.getBalls();
//            BallRepository ballDao = new BallRepository();
//            for (Ball ball : balls) {
//                ballDao.addBall(ball, over_id);
//            }
//        }
//    }

    public void getOver(int innings_id) throws SQLException {
        String sqlQuery = "select * from overs where innings_id = " + innings_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.format("\n%10s%10s%10s\n", "Over", "Runs", "Wickets");
        int k = 1;
        while (rs.next()) {
            System.out.format("%10d%10d%10d\n", k++, rs.getInt(2), rs.getInt(3));
        }
        rs.close();
    }

    public Over getOverDetail(int over_id) throws SQLException {
        String sqlQuery = "select * from overs where over_id = " + over_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        Over over = new Over();
        if(!rs.next()){
            return null;
        }
        else{
            over.setOverId(over_id);
            over.setRunsScored(rs.getInt(2));
            over.setWicketsTaken(rs.getInt(3));
            over.setBalls(ballRepository.getBallsSummary(over_id));
        }
        return over;
    }
    public List<Over> getOverSummary(int innings_id) throws SQLException {
        String sqlQuery = "select * from overs where innings_id = " + innings_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        List<Over> overs = new ArrayList<>();
        while (rs.next()) {
            Over over = new Over();
            over.setOverId(rs.getInt(1));
            over.setRunsScored(rs.getInt(2));
            over.setWicketsTaken(rs.getInt(3));
            overs.add(over);
        }
        rs.close();
        return overs;
    }

}
