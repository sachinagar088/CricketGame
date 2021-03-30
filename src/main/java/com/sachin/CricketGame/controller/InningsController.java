package com.sachin.CricketGame.controller;

import com.sachin.CricketGame.models.Innings;
import com.sachin.CricketGame.models.Over;
import com.sachin.CricketGame.models.Wicket;
import com.sachin.CricketGame.service.InningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InningsController {

    @Autowired
    InningsService inningsService;

    @GetMapping("/innings/{inningsId}")
    public Innings getInningsSummary(@PathVariable int inningsId) {
        return inningsService.getSummary(inningsId);
    }

    @GetMapping("/innings/{inningsId}/overs")
    public List<Over> getOverSummaryInInnings(@PathVariable int inningsId) {
        return inningsService.getOverSummary(inningsId);
    }

    @GetMapping("/innings/{inningsId}/wickets")
    public List<Wicket> getWicketSummaryInInnings(@PathVariable int inningsId) {
        return inningsService.getWicketSummary(inningsId);
    }

    @GetMapping("/match/{matchId}/innings/{inningsId}/play")
    public Innings startInnings(@PathVariable int matchId,@PathVariable int inningsId) {
        return inningsService.startInnings(matchId,inningsId);
    }
}
