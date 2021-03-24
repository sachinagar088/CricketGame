package com.sachin.CricketGame.Repository;

import com.sachin.CricketGame.models.Player;
import com.sachin.CricketGame.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {

    @Autowired
    TeamPlayersRepository teamPlayersRepository;

    static Connection conn = DatabaseConnection.getConnection();

    public Player addIndividualPlayer(Player player) throws SQLException {
        String sqlQuery = "insert into Player (Player_Name,Player_role) values (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,player.getName());
        preparedStatement.setString(2,player.getPlayerRole());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            player.setPlayerId(rs.getInt(1));
        }
        return player;
    }

    public void addPlayer(Player player, int team_id) throws SQLException {
        String sqlQuery = "insert into team_players values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setInt(1,team_id);
        preparedStatement.setInt(2,player.getPlayerId());
        preparedStatement.setInt(3,player.getRunsScored());
        preparedStatement.setInt(4,player.getBallsTaken());
        preparedStatement.setInt(5,player.getStrikeRate());
        preparedStatement.setBoolean(6, player.isOutStatus());
        preparedStatement.executeUpdate();
    }
    public void updatePlayer(Player player) throws SQLException {
        String sqlQuery = "update Player set Player_Name = ?,Player_role = ?,noOfMatches = ?,avg_runs = ?," +
                "avg_strike_rate = ? where Player_Id = " + player.getPlayerId();
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1,player.getName());
        preparedStatement.setString(2,player.getPlayerRole());
        preparedStatement.setInt(3,player.getNoOfMatches());
        preparedStatement.setInt(4,player.getAvgRuns());
        preparedStatement.setInt(5,player.getAvgStrikeRate());
        preparedStatement.executeUpdate();
    }

    public void updatePlayer(Player player,int team_id) throws SQLException {
        String sqlQuery = "update team_players set Runs_Scored = ?,Balls_taken = ?,strike_rate = ?,isOut = ? " +
                "where player_id  = "+player.getPlayerId()+" and team_id = "+team_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, player.getRunsScored());
        preparedStatement.setInt(2, player.getBallsTaken());
        preparedStatement.setInt(3, player.getStrikeRate());
        preparedStatement.setBoolean(4, player.isOutStatus());
        preparedStatement.executeUpdate();
    }

    public void getPlayer(int player_id,int team_id) throws SQLException {
        String sqlQuery = "select player.Player_Name,player.Player_role,teamPlayers.Runs_Scored," +
                "teamPlayers.Balls_taken,teamPlayers.strike_rate,teamPlayers.isOut from Player player," +
                "team_players teamPlayers where teamPlayers.team_id = " + team_id + " and teamPlayers.player_id = " +player_id+ " and player.Player_Id" +
                "= teamPlayers.player_id";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            System.out.format("%10s%10d%10d%10s\n",rs.getString(1),rs.getInt(3),
                    rs.getInt(4),rs.getBoolean(6));
        }
    }
    public Player getPlayerDetails(int player_id) throws SQLException {
        String sqlQuery = "select * from Player where Player_Id = " + player_id;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        Player player = new Player();
        if (!rs.next()) {
            return null;
        }
        else {
            player.setPlayerId(player_id);
            player.setName(rs.getString(2));
            player.setPlayerRole(rs.getString(3));
            player.setNoOfMatches(rs.getInt(4));
            player.setAvgRuns(rs.getInt(5));
            player.setAvgStrikeRate(rs.getInt(6));
        }
        rs.close();
        return player;
    }
    public Player getPlayerSummary(int player_id,int team_id) throws SQLException {
        String sqlQuery = "select player.Player_Name,player.Player_role,player.noOfMatches," +
                "player.avg_runs,player.avg_strike_rate,teamPlayers.Runs_Scored," +
                "teamPlayers.Balls_taken,teamPlayers.strike_rate,teamPlayers.isOut from Player player," +
                "team_players teamPlayers where teamPlayers.team_id = " + team_id + " and teamPlayers.player_id = " +player_id+ " and player.Player_Id" +
                "= teamPlayers.player_id";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        Player player = new Player();
        if (rs.next()) {
            player.setPlayerId(player_id);
            player.setName(rs.getString(1));
            player.setPlayerRole(rs.getString(2));
            player.setNoOfMatches(rs.getInt(3));
            player.setAvgRuns(rs.getInt(4));
            player.setAvgStrikeRate(rs.getInt(5));
            player.setRunsScored(rs.getInt(6));
            player.setBallsTaken(rs.getInt(7));
            player.setStrikeRate(rs.getInt(8));
            player.setOutStatus(rs.getBoolean(9));
        }
        rs.close();
        return player;
    }

    public List<Player> getPlayersInMatch(int matchId) throws SQLException{
        String sqlQuery = "select * from matches where id = "+matchId;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        List<Player> players = new ArrayList<>();
        if(rs.next()){
            players.addAll(teamPlayersRepository.getTeamPlayersSummary(rs.getInt(2)));
            players.addAll(teamPlayersRepository.getTeamPlayersSummary(rs.getInt(3)));
        }
        return players;
    }

    public Player getPlayerInMatch(int matchId,int playerId) throws SQLException{
        String sqlQuery = "select * from matches where id = "+matchId;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        Player player = null;
        if(rs.next()){
            String sqlQuery1 = "select player_id from team_players where team_id = " +rs.getInt(2);
            PreparedStatement preparedStatement1 = conn.prepareStatement(sqlQuery1);
            ResultSet rs1 = preparedStatement1.executeQuery();
            while(rs1.next()){
                if(rs1.getInt(1)==playerId){
                    player = getPlayerSummary(playerId,rs.getInt(2));
                    break;
                }
            }
            if(player==null) {
                String sqlQuery2 = "select player_id from team_players where team_id = " + rs.getInt(3);
                PreparedStatement preparedStatement2 = conn.prepareStatement(sqlQuery2);
                ResultSet rs2 = preparedStatement2.executeQuery();
                while (rs2.next()) {
                    if (rs2.getInt(1) == playerId) {
                        player = getPlayerSummary(playerId, rs.getInt(3));
                    }
                }
            }
        }
        return player;
    }
}
