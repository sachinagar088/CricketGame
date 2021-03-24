package com.sachin.CricketGame.models;

import com.sachin.CricketGame.util.Util;

public class Toss {

    public void chooseBatOrBowl(Cricket cricket,Team teamA,Team teamB,Input input){
        if((input.getString("Batting or Bowling")).equals("Batting")){
            cricket.setTeamBattingFirst(teamA.getTeamID());
        }
        else{
            cricket.setTeamBattingFirst(teamB.getTeamID());
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
        System.out.println("Team "+c.getTeamBattingFirst()+" is batting First");
    }
}
