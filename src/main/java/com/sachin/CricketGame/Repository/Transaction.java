package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class Transaction {
    static Connection conn = DatabaseConnection.getConnection();

    public void startTransaction(){
        String sqlQuery = "START TRANSACTION";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
    public void commitTransaction(){
        String sqlQuery = "COMMIT";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
}
