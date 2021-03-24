package com.sachin.CricketGame.models;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ChooseNextBatsman implements nextBatsman {

    @Override
    public Player SequentialNextBatsman(List<Player> players, int next) {
        return players.get(next);
    }

    @Override
    public Player RandomNextBatsman(List<Player> players) {
        List<Player> sortPlayers = players.stream().filter(player -> !(player.isOutStatus())).collect(Collectors.toList());
        return sortPlayers.get(new Random().nextInt(sortPlayers.size()));
    }

    @Override
    public Player basedOnStrikeRate(List<Player> players) {
       players.sort(Comparator.comparingInt(Player::getStrikeRate));
        return players.get(0);
    }
}
