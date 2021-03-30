package com.sachin.CricketGame.models;

import com.sachin.CricketGame.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Toss {

    @Autowired
    Match match;

    public void chooseBatOrBowl(Cricket cricket,Team teamA,Team teamB,Input input){
        if((input.getString("Batting or Bowling")).equals("Batting")){
            System.out.println("Team " + teamA.getTeamName() + " is batting First");
            match.playMatch(cricket, teamA, teamB);
        }
        else{
            System.out.println("Team " + teamB.getTeamName() + " is batting First");
            match.playMatch(cricket, teamB, teamA);
        }
    }

    public void tossCoin(Cricket c){
        Input input = new Input();
        String choice= input.getString("Choose Head (H) or Tail (T): ");
        if(choice.equals(Util.generateRandomCoinFace())){
            System.out.println("Team "+c.getTeamOne().getTeamName()+" won the toss");
            chooseBatOrBowl(c,c.getTeamOne(),c.getTeamTwo(),input);
        }
        else{
            System.out.println("Team "+c.getTeamTwo().getTeamName()+" won the toss");
            chooseBatOrBowl(c,c.getTeamTwo(),c.getTeamOne(),input);
        }
    }
}
