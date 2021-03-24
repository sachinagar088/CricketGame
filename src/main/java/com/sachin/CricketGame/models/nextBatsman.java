package com.sachin.CricketGame.models;

import java.util.List;

public interface nextBatsman {
    Player SequentialNextBatsman(List<Player> players ,int next);
    Player RandomNextBatsman(List<Player> players);
    Player basedOnStrikeRate(List<Player> players);
}
