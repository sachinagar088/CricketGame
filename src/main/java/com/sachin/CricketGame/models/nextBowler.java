package com.sachin.CricketGame.models;

import java.util.List;

public interface nextBowler {
    Bowler SequentialNextBowler(List<Bowler> bowler ,int next);
    Bowler RandomNextBowler(List<Bowler> bowler);
}
