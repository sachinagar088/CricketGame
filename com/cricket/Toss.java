package com.cricket;

public class Toss {

    public void chooseBatOrBowl(Cricket cricket,Team teamA,Team teamB,Input input){
        if((input.getString("Batting or Bowling")).equals("Batting")){
            cricket.setTeamBattingFirst(teamA.getTeamID());
        }
        else{
            cricket.setTeamBattingFirst(teamB.getTeamID());
        }
    }

    public void tossCoin(Cricket c,Team t1,Team t2){
        Input input = new Input();
        String choice= input.getString("Choose Head (H) or Tail (T): ");
        if(choice.equals(Util.generateRandomCoinFace())){
            System.out.println("Team "+t1.getTeamID()+" won the toss");
            chooseBatOrBowl(c,t1,t2,input);
        }
        else{
            System.out.println("Team "+t2.getTeamID()+" won the toss");
            chooseBatOrBowl(c,t2,t1,input);
        }
        System.out.println("Team "+c.getTeamBattingFirst()+" is batting First");
    }
}
