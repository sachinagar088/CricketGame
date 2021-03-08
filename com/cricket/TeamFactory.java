package com.cricket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamCreation {

    public static TeamCreation getInstance(){
        return new TeamCreation();
    }

    public Team createTeam(int id){
        System.out.println("\nEnter team "+id+" Details\n");
        Scanner sc=new Scanner(System.in);
        List<Player> pl= new ArrayList<>();
        Team t=Team.getInstance();
        System.out.println("Enter Team Name");
        t.setTeamName(sc.next());
        for(int i=0;i<3;i++){
            Player p= Player.getInstance();
            System.out.println("Enter Player Id");
            p.setPlayerId(sc.nextInt());
            System.out.println("Enter Player Name");
            p.setName(sc.next());
            System.out.println("Enter Player Role");
            p.setPlayerRole(sc.next());
            p.setRunsScored(0);
            p.setBallsTaken(0);
            pl.add(p);
        }
        t.setTeamID(id);
        t.setPlayers(pl);
        t.setTotalWicketsFall(0);
        t.setTeamScore(0);
        return t;
    }
}
