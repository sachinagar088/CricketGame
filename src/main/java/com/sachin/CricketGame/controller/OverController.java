package com.sachin.CricketGame.controller;

import com.sachin.CricketGame.Repository.OverRepository;
import com.sachin.CricketGame.exception.RecordNotFound;
import com.sachin.CricketGame.models.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OverController {

    @Autowired
    OverRepository overRepository;

    @GetMapping("/over/{overId}")
    public Over getOverSummary(@PathVariable int overId) {
        Over over = overRepository.getOverDetail(overId);
        if(over == null){
            throw new RecordNotFound("Enter a valid Over ID");
        }
        return over;
    }

}
