package com.sachin.CricketGame.models;

import com.sachin.CricketGame.Repository.InningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class Match {

    @Autowired
    InningsRepository inningsRepository;

    public void playMatch(Cricket cricket,Team teamOne,Team teamTwo) throws SQLException {
        Innings firstInnings = new Innings(teamOne,teamTwo,0);
        int first_innings = inningsRepository.addInningsSummary(firstInnings);
        firstInnings.setInnings_id(first_innings);
        cricket.setInnings_one(firstInnings);
//        firstInnings.startInnings(cricket);
//        System.out.println("\nFirst Innings Over");
//        ScoreCard.teamSummary(teamOne,teamTwo);
//        System.out.format("\n%30s\n","First Innings Summary");
//        ScoreCard.inningsSummary(firstInnings);
        Innings secondInnings = new Innings(teamTwo,teamOne,1);
        int second_innings = inningsRepository.addInningsSummary(secondInnings);
        secondInnings.setInnings_id(second_innings);
        cricket.setInnings_two(secondInnings);
//        secondInnings.startInnings(cricket);
//        cricket.setInnings_one(firstInnings);
//        cricket.setInnings_two(secondInnings);
//        System.out.println("\nSecond Innings Over");
//        ScoreCard.teamSummary(teamTwo,teamOne);
//        System.out.format("%20s\n","Second Innings Summary");
//        ScoreCard.inningsSummary(secondInnings);
//        System.out.println("\nTeam "+cricket.getWinning_team().getTeamName()+" "+cricket.getComments());
    }
}
