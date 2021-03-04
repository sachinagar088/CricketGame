package com.cricket;

import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.*;

public class MatchMethod {
    public static MatchMethod getMethod(){
        return new MatchMethod();
    }

    public List<Player> swap(Player striker,Player nonStriker)
    {
        List<Player> p=new ArrayList<>();
        Player temp=striker;
        striker=nonStriker;
        nonStriker=temp;
        p.add(striker);
        p.add(nonStriker);
        return p;

    }
    public void battingFirst(Cricket c,Team t){
        int wicket=0;
        int allOut=0;
        int totalScore=0;
        int st=0;
        int nonSt=1;
        Player striker= t.getPlayers().get(st);
        Player nonStriker = t.getPlayers().get(nonSt);
        for(int i=0;i<c.getTotalOvers();i++){
            for(int j=0;j<6;j++){
                int score=c.randomNumbers();
                if(score==7){
                    wicket=wicket+1;
                    if(wicket==c.getTotalWickets()){
                        System.out.println("WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        System.out.println(format("Team {0} All Out", t.getTeamID()));
                        allOut=1;
                        break;
                    }
                    else{
                        System.out.println("WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        if(st>nonSt) {
                            st += 1;
                            striker = t.getPlayers().get(st);
                        }
                        else {
                            nonSt += 1;
                            striker = t.getPlayers().get(nonSt);
                        }
                    }
                }
                else {
                    System.out.println(score + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score);
                    totalScore+=score;
                    if(score==1 || score==3){
                        List<Player> sw = swap(striker,nonStriker);
                        striker = sw.get(0);
                        nonStriker = sw.get(1);
                    }
                }
            }
            if(allOut==1){
                break;
            }
            else{
                List<Player> sw = swap(striker,nonStriker);
                striker = sw.get(0);
                nonStriker = sw.get(1);
            }
        }
        t.setTeamScore(totalScore);
        t.setTotalWicketsFall(wicket);
        c.setBattingFirstScore(totalScore);
        c.setTotalWicketsBattingFirst(wicket);
    }
    public void battingSecond(Cricket c,Team t){
        int wicket1=0;
        int totalScore1=0;
        int allOut=0;
        int win=0;
        int st=0;
        int nonSt=1;
        Player striker= t.getPlayers().get(st);
        Player nonStriker = t.getPlayers().get(nonSt);
        for(int i=0;i<c.getTotalOvers();i++){
            for(int j=0;j<6;j++){
                int score1 = c.randomNumbers();
                if(score1==7){
                    wicket1+=1;
                    if(wicket1==c.getTotalWickets()){
                        if(totalScore1<c.getBattingFirstScore()){
                            System.out.println("WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                            System.out.println("Team "+t.getTeamID()+" All Out");
                            allOut=1;
                            break;
                        }

                    }
                    else{
                        System.out.println("WICKET!!!" + striker.getName()+" is Out at "+striker.getRunsScored());
                        if(st>nonSt) {
                            st += 1;
                            striker = t.getPlayers().get(st);
                        }
                        else {
                            nonSt += 1;
                            striker = t.getPlayers().get(nonSt);
                        }
                    }
                }
                else {
                    System.out.println(score1 + " run " +" hit by " + striker.getName());
                    striker.setRunsScored(striker.getRunsScored()+score1);
                    totalScore1+=score1;
                    if(score1==1 || score1==3){
                        List<Player> sw = swap(striker,nonStriker);
                        striker = sw.get(0);
                        nonStriker = sw.get(1);
                    }
                    if (totalScore1 > c.getBattingFirstScore()) {
                        System.out.println("Team "+t.getTeamID()+" Score:" +totalScore1+"/"+wicket1);
                        System.out.println("Team "+t.getTeamID()+" won ");
                        win = 1;
                        break;
                    }
                }
            }
            if(allOut==1 || win==1)
                break;
            else{
                List<Player> sw = swap(striker,nonStriker);
                striker = sw.get(0);
                nonStriker = sw.get(1);
            }
        }
        if(win==0) {
            System.out.println("Team "+t.getTeamID()+" Score:"+totalScore1+"/"+wicket1);
            System.out.println("Team "+c.getTeamBattingFirst()+" won by:" + (c.getBattingFirstScore() - totalScore1));
        }
        t.setTeamScore(totalScore1);
        t.setTotalWicketsFall(wicket1);
    }
}
