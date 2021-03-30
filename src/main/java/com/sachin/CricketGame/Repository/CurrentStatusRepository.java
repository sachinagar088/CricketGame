//package com.sachin.CricketGame.Repository;
//
//import com.sachin.CricketGame.util.DatabaseConnection;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class CurrentStatusRepository {
//
//    static Connection conn = DatabaseConnection.getConnection();
//    public void insertCurrentStatus(int striker,int nonStriker){
//        String sqlQuery = "insert into currentBatsman values (?,?)";
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//            preparedStatement.setInt(1,striker);
//            preparedStatement.setInt(2,nonStriker);
//            preparedStatement.executeUpdate();
//        }catch(SQLException exception){
//            exception.printStackTrace();
//        }
//    }
//    public void updateCurrentStatus(int striker,int nonStriker){
//        String sqlQuery = "update currentBatsman set striker = ?, nonStriker = ?";
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//            preparedStatement.setInt(1,striker);
//            preparedStatement.setInt(2,nonStriker);
//            preparedStatement.executeUpdate();
//        }catch(SQLException exception){
//            exception.printStackTrace();
//        }
//
//    }
//    public List<Integer> getCurrentStatus(){
//        String sqlQuery = "select * from currentBatsman";
//        List<Integer> player_id = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                player_id.add(resultSet.getInt(1));
//                player_id.add(resultSet.getInt(2));
//            }
//        }catch(SQLException exception){
//            exception.printStackTrace();
//        }
//        return player_id;
//    }
//
//}
