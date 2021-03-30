package com.sachin.CricketGame.models;

import java.util.List;
import java.util.Random;

public class ChooseNextBowler implements nextBowler {

    @Override
    public Bowler SequentialNextBowler(List<Bowler> bowlers, int next) {
        return bowlers.get(next % bowlers.size());
    }

    @Override
    public Bowler RandomNextBowler(List<Bowler> bowlers) {
        return bowlers.get(new Random().nextInt(bowlers.size()));
    }
}
