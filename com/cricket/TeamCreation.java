package com.cricket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamCreation {

    public static TeamCreation getInstance(){
        return new TeamCreation();
    }

    public Team add(int id){
        System.out.println("Enter team Details");
        System.out.println();
        Scanner sc=new Scanner(System.in);
        List<Player> pl= new ArrayList<>();
        Team t=Team.getInstance();
        for(int i=0;i<3;i++){
            Player p= Player.getInstance();
            System.out.println("Enter Player Id");
            p.setPlayerId(sc.nextInt());
            System.out.println("Enter Player Name");
            p.setName(sc.next());
            System.out.println("Enter Player Role");
            p.setPlayerRole(sc.next());
            p.setRunsScored(0);
            pl.add(p);
        }
        t.setTeamID(id);
        t.setPlayers(pl);
        t.setTotalWicketsFall(0);
        t.setTeamScore(0);
        return t;
    }
    public void view (Team t){
        System.out.println("Team ID : "+ t.getTeamID());
        for(Player p : t.getPlayers()) {
            System.out.println(p.getPlayerId() + " " + p.getName() + " " + p.getPlayerRole());
        }
    }
}
