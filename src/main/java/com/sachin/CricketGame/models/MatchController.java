package com.sachin.CricketGame.models;

import com.sachin.CricketGame.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MatchController {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    Match match;

//    public void addMatchDetails(Cricket cricket,int match_id) throws SQLException{
//        TeamRepository teamDao = new TeamRepository();
//        teamDao.updateTeam(cricket.getTeamOne());
//        teamDao.updateTeam(cricket.getTeamTwo());
//        InningsRepository inningsDao = new InningsRepository();
//        int first_innings_id = inningsDao.addInningsSummary(cricket.getInnings_one());
//        cricket.getInnings_one().setInnings_id(first_innings_id);
//        int second_innings_id = inningsDao.addInningsSummary(cricket.getInnings_two());
//        cricket.getInnings_two().setInnings_id(second_innings_id);
//        MatchRepository matchDao = new MatchRepository();
//        matchDao.updateMatch(cricket,match_id);
//    }
    public void start(Cricket cricket) throws SQLException {
        //Cricket c = new Cricket();
        Toss toss = new Toss();
//        c.setTeamOne(team1);
//        c.setTeamTwo(team2);
        cricket.setStatus("In_progress");
        System.out.println(cricket.getTeamOne());
        System.out.println(cricket.getTeamTwo());
        toss.tossCoin(cricket);
        System.out.println();
        if(cricket.getTeamBattingFirst()==cricket.getTeamOne().getTeamID()) {
            match.playMatch(cricket, cricket.getTeamOne(), cricket.getTeamTwo());
        }
        else{
            match.playMatch(cricket, cricket.getTeamTwo(), cricket.getTeamOne());
        }
        matchRepository.updateMatch(cricket,cricket.getMatch_id());
        //addMatchDetails(c,match_id);
    }
}
