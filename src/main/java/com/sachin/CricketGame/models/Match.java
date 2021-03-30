package com.sachin.CricketGame.models;

import com.sachin.CricketGame.Repository.InningsRepository;
import com.sachin.CricketGame.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Match {

    @Autowired
    InningsRepository inningsRepository;
    @Autowired
    MatchRepository matchRepository;

    public void playMatch(Cricket cricket,Team teamOne,Team teamTwo) {
        Innings firstInnings = new Innings(teamOne,teamTwo,0);
        int first_innings = inningsRepository.addInningsSummary(firstInnings);
        firstInnings.setInnings_id(first_innings);
        cricket.setInnings_one(firstInnings);
        Innings secondInnings = new Innings(teamTwo,teamOne,1);
        int second_innings = inningsRepository.addInningsSummary(secondInnings);
        secondInnings.setInnings_id(second_innings);
        cricket.setInnings_two(secondInnings);
        matchRepository.updateMatch(cricket);
    }
}
