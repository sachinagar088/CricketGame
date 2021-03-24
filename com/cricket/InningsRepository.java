package com.sachin.CricketGame.Repository;


import com.sachin.CricketGame.models.Innings;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class InningsRepository {

    @Autowired
    private OverRepository overRepository;
    @Autowired
    private WicketRepository wicketRepository;
    @Autowired
    private TeamRepository teamRepository;

    static Connection conn = DatabaseConnection.getConnection();

    public int addInningsSummary(Innings innings) throws SQLException{
        int innings_id = 0;
        String sqlQuery = "insert into innings (batting_team_id,balling_team_id," +
                "runs,wickets,overs) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, innings.getTeam().getTeamID());
        preparedStatement.setInt(2, innings.getTeamTwo().getTeamID());
        preparedStatement.setInt(3, innings.getTeam().getTeamScore());
        preparedStatement.setInt(4, innings.getWicket());
        preparedStatement.setFloat(5, innings.getOversDone());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            innings_id = rs.getInt(1);
        }
        return innings_id;
    }

    public void updateInningsSummary(Innings innings) throws SQLException {
        String sqlQuery = "update innings set runs = ?,wickets=?,overs = ? where id = "+innings.getInnings_id();
//        String sqlQuery = "insert into innings (batting_team_id,balling_team_id," +
//                "runs,wickets,overs) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//        preparedStatement.setInt(1, innings.getTeam().getTeamID());
//        preparedStatement.setInt(2, innings.getTeamTwo().getTeamID());
        preparedStatement.setInt(1, innings.getTeam().getTeamScore());
        preparedStatement.setInt(2, innings.getWicket());
        preparedStatement.setFloat(3, innings.getOversDone());
        preparedStatement.executeUpdate();
////        ResultSet rs = preparedStatement.getGeneratedKeys();
////        if (rs.next()) {
////            innings_id = rs.getInt(1);
//            OverRepository overDao = new OverRepository();
//            WicketRepository wicketDao = new WicketRepository();
//            List<Over> overs = innings.getOvers();
//            List<Wicket> wickets = innings.getWickets();
//            for (Over over : overs) {
//                overDao.addOver(over, innings.getInnings_id());
//            }
//            for (Wicket wicket : wickets) {
//                wicketDao.addWicket(wicket, innings.getInnings_id());
//            }
    }

    public void getInningsSummary(int innings_id) throws SQLException {
        String sqlQuery = "select id,runs,overs,wickets," +
                "battingTeam.team_name,ballingTeam.team_name from innings " +
                "inner join team as battingTeam on batting_team_id = battingTeam.team_id " +
                "inner join team as ballingTeam on balling_team_id = ballingTeam.team_id and id = " + innings_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            System.out.println("\nBatting team : " + rs.getString(5));
            System.out.println("Balling team : " + rs.getString(6));
            System.out.format("%10s%10s%10s\n", "Runs", "Wicket", "Overs");
            System.out.format("%10d%10d%10.1f\n", rs.getInt(2), rs.getInt(4), rs.getFloat(3));
            overRepository.getOver(innings_id);
            wicketRepository.getWicket(innings_id);
        }
        rs.close();
    }

    public Innings getInningSummary(int innings_id) throws SQLException {
        String sqlQuery = "select id,runs,overs,wickets,batting_team_id,balling_team_id from innings " +
                "where id = " + innings_id;
////                "battingTeam.team_name,ballingTeam.team_name from innings " +
//                "inner join team as battingTeam on batting_team_id = battingTeam.team_id " +
//                "inner join team as ballingTeam on balling_team_id = ballingTeam.team_id and id = " + innings_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        Innings innings = null;
        if (rs.next()) {
            innings = new Innings(rs.getFloat(3),rs.getInt(4),wicketRepository.getWicketSummary(innings_id),
                    overRepository.getOverSummary(innings_id),teamRepository.getTeamSummary(rs.getInt(5)),
                    teamRepository.getTeamSummary(rs.getInt(6)));
            innings.setInnings_id(innings_id);
        }
        rs.close();
        return innings;
    }
}
