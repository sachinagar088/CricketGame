package com.cricket;

import static java.text.MessageFormat.*;

public class MatchMethod {
    public static MatchMethod getMethod(){
        return new MatchMethod();
    }
    public void battingFirst(Cricket c){
        int wicket=0;
        int allOut=0;
        int totalScore=0;
        for(int i=0;i<c.getTotalOvers();i++){
            for(int j=0;j<6;j++){
                int score=c.randomNumbers();
                if(score==7){
                    wicket=wicket+1;
                    if(wicket==c.getTotalWickets()){
                        System.out.println(format("Team {0} All Out", c.getTeamBattingFirst()));
                        allOut=1;
                        break;
                    }
                }
                else {
                    totalScore+=score;
                }
            }
            if(allOut==1){
                break;
            }
        }
        c.setBattingFirstScore(totalScore);
        c.setTotalWicketsBattingFirst(wicket);
    }

    public void battingSecond(int team,Cricket c){
        int wicket1=0;
        int totalScore1=0;
        int allOut=0;
        int win=0;
        for(int i=0;i<c.getTotalOvers();i++){
            for(int j=0;j<6;j++){
                int score1 = c.randomNumbers();
                if(score1==7){
                    wicket1+=1;
                    if(wicket1==c.getTotalWickets()){
                        if(totalScore1<c.getBattingFirstScore()){
                            System.out.println("Team "+team+" All Out");
                            allOut=1;
                            break;
                        }

                    }
                }
                else {
                    totalScore1+=score1;
                    if (totalScore1 > c.getBattingFirstScore()) {
                        System.out.println("Team "+team+" Score:" +totalScore1+"/"+wicket1);
                        System.out.println("Team "+team+" won ");
                        win = 1;
                        break;
                    }
                }
            }
            if(allOut==1)
                break;
            if(win==1)
                break;
        }
        if(win==0) {
            System.out.println("Team "+team+" Score:"+totalScore1+"/"+wicket1);
            System.out.println("Team "+c.getTeamBattingFirst()+" won by:" + (c.getBattingFirstScore() - totalScore1));
        }
    }
}
