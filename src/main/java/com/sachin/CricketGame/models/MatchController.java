package com.sachin.CricketGame.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchController {

    @Autowired
    Toss toss;

    public void start(Cricket cricket) {
        cricket.setStatus("In_progress");
        System.out.println(cricket.getTeamOne());
        System.out.println(cricket.getTeamTwo());
        toss.tossCoin(cricket);
        System.out.println();
    }
}
