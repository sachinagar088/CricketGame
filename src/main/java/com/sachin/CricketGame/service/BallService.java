package com.sachin.CricketGame.service;

import com.sachin.CricketGame.Repository.*;
import com.sachin.CricketGame.models.*;
import com.sachin.CricketGame.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BallService {

    @Autowired
    WicketRepository wicketRepository;
    @Autowired
    BallRepository ballRepository;
    @Autowired
    OverRepository overRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    BowlerRepository bowlerRepository;
    @Autowired
    TeamRepository teamRepository;

    public float oversDone(float overs) {
        float updateOvers = overs + (float) 0.1;
        if ((updateOvers * 10) % 10 == 6.0) {
            updateOvers = (float) ((int) (updateOvers * 10) / 10) + (float) 1.0;
        }
        return updateOvers;
    }

    public Ball playBall(Bowler currentBowler, Player striker, Over over, Team teamOne, Team teamTwo, int overNumber, int ballNumber, int innings_id,int score) {
        //int score = Util.randomNumbers(striker.getPlayerRole());
        striker.setBallsTaken(striker.getBallsTaken() + 1);
        currentBowler.setOversDone(oversDone(currentBowler.getOversDone()));
        Ball ball = new Ball(overNumber + "." + ballNumber, score, striker.getName(), currentBowler.getName());
        ballRepository.addBall(ball, over.getOverId());
        if (score == 7) {
            Wicket wicket_down = new Wicket(overNumber + "." + ballNumber, striker, currentBowler);
            wicketRepository.addWicket(wicket_down, innings_id);
            striker.setOutStatus(true);
            striker.calculateStrikeRate();
            playerRepository.updatePlayer(striker, teamOne.getTeamID());
            System.out.println(printBall(overNumber, ballNumber) + " WICKET!!!" + striker.getName() + " is Out at " + striker.getRunsScored());
            currentBowler.setWicketsTaken(currentBowler.getWicketsTaken() + 1);
            bowlerRepository.updateBowler(currentBowler, teamTwo.getTeamID());
            over.setWicketsTaken(over.getWicketsTaken() + 1);
            overRepository.updateOver(over);
            teamOne.setTotalWicketsFall(teamOne.getTotalWicketsFall() + 1);
            teamRepository.updateTeam(teamOne);
        } else {
            System.out.println(printBall(overNumber, ballNumber) + " " + score + " run " + " hit by " + striker.getName());
            striker.setRunsScored(striker.getRunsScored() + score);
            striker.calculateStrikeRate();
            playerRepository.updatePlayer(striker, teamOne.getTeamID());
            currentBowler.setRunsGiven(currentBowler.getRunsGiven() + score);
            bowlerRepository.updateBowler(currentBowler, teamTwo.getTeamID());
            over.setRunsScored(over.getRunsScored() + score);
            overRepository.updateOver(over);
            teamOne.setTeamScore(teamOne.getTeamScore() + score);
            teamRepository.updateTeam(teamOne);
        }
        return ball;
    }

    public String printBall(int i, int j) {
        return (i) + "." + (j);
    }
}
