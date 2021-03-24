package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.models.Cricket;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

import static java.sql.Types.NULL;

@Repository
public class MatchRepository {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    InningsRepository inningsRepository;

    static Connection conn = DatabaseConnection.getConnection();

    public int addMatch(Cricket cricket) throws SQLException {
        int match_id = 0;
        String sqlQuery = "insert into matches (team_one,team_two,venue,overs,status,date) values (?,?,?,?,?,now())";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, cricket.getTeamOne().getTeamID());
        preparedStatement.setInt(2, cricket.getTeamTwo().getTeamID());
        preparedStatement.setString(3, cricket.getVenue());
        preparedStatement.setInt(4, Cricket.getNoOfOvers());
        preparedStatement.setString(5,"scheduled");
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            match_id = rs.getInt(1);
        }
        return match_id;
    }

    public void updateMatch(Cricket cricket,int matchId) throws SQLException {
        String sqlQuery = "update matches set innings_one = ?,innings_two = ?,winning_team_id = ?," +
                "comment = ?,status = ? where id = " + matchId;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, cricket.getInnings_one().getInnings_id());
        preparedStatement.setInt(2, cricket.getInnings_two().getInnings_id());
        if(cricket.getWinning_team()==null)
            preparedStatement.setNull(3,NULL);
        else
            preparedStatement.setInt(3,cricket.getWinning_team().getTeamID());
        preparedStatement.setString(4, cricket.getComments());
        preparedStatement.setString(5, cricket.getStatus());
        preparedStatement.executeUpdate();
    }
    public void getMatchSummary(int id) throws SQLException {
        String sqlQuery = "select id,team_one,team_two,innings_one,innings_two,venue,overs,comment," +
                "teamOne.team_name,teamTwo.team_name, winning.team_name,date from matches " +
                "inner join team as teamOne on team_one = teamOne.team_id " +
                "inner join team as teamTwo on team_two = teamTwo.team_id " +
                "inner join team as winning on winning_team_id = winning.team_id and id = " + id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString(9) + " VS " + rs.getString(10));
            System.out.println("Match Venue  : " + rs.getString(6));
            System.out.println("Total Overs  : " + rs.getInt(7));
            System.out.println("Date : " + rs.getDate(12));
            System.out.println("Winning team : " + rs.getString(11) + " " + rs.getString(8));
            teamRepository.getTeam(rs.getInt(2));
            teamRepository.getTeam(rs.getInt(3));
            System.out.format("\n%30s\n", "First Innings Summary");
            inningsRepository.getInningsSummary(rs.getInt(4));
            System.out.format("\n%30s\n", "Second Innings Summary");
            inningsRepository.getInningsSummary(rs.getInt(5));
        }
        rs.close();
    }
    public Cricket getGameSummary(int id) throws SQLException {
        String sqlQuery = "select id,team_one,team_two,innings_one,innings_two,winning_team_id,venue,overs,comment,date,status" +
                " from matches where id = " +id;
//                "inner join team as teamOne on team_one = teamOne.team_id " +
//                "inner join team as teamTwo on team_two = teamTwo.team_id " +
//                "inner join team as winning on winning_team_id = winning.team_id ";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        Cricket cricket = new Cricket();
        if(!rs.next()){
            return null;
        }
        else {
            cricket.setMatch_id(rs.getInt(1));
            cricket.setVenue(rs.getString(7));
            cricket.setDate(rs.getDate(10));
            cricket.setComments(rs.getString(9));
            cricket.setTeamOne(teamRepository.getTeamSummary(rs.getInt(2)));
            cricket.setTeamTwo(teamRepository.getTeamSummary(rs.getInt(3)));
            cricket.setInnings_one(inningsRepository.getInningSummary(rs.getInt(4)));
            cricket.setInnings_two(inningsRepository.getInningSummary(rs.getInt(5)));
            cricket.setWinning_team(teamRepository.getTeamSummary(rs.getInt(6)));
            cricket.setStatus(rs.getString(11));
        }
        rs.close();
        return cricket;
    }
}
