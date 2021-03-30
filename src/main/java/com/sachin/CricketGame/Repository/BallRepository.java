package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.exception.CustomException;
import com.sachin.CricketGame.models.Ball;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BallRepository {
    static Connection conn = DatabaseConnection.getConnection();

    public void addBall(Ball ball, int over_id){
        String sqlQuery = "insert into ball values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, ball.getBallThrown());
            preparedStatement.setInt(2, ball.getRunsScored());
            preparedStatement.setString(3, ball.getBatsman());
            preparedStatement.setString(4, ball.getBowler());
            preparedStatement.setInt(5, over_id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            //sqlException.printStackTrace();
            throw new CustomException(sqlException.getMessage());
        }
    }

    public List<Ball> getBallsSummary(int over_id) {
        String sqlQuery = "select * from ball where over_id = " + over_id;
        List<Ball> balls = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ball ball = new Ball(resultSet.getString(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4));
                balls.add(ball);

            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return balls;
    }
}
