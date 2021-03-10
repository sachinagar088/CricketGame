package com.cricket;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ChooseNextBatsman implements nextPlayer{
    @Override
    public Player nextSequential(Team team, int next){
        return team.getPlayers().get(next);
    }

    @Override
    public Player RandomNext(Team team) {
        List<Player> players = team.getPlayers().stream().filter(player -> !(player.isOutStatus())).collect(Collectors.toList());
        return players.get(new Random().nextInt(players.size()));
    }

    @Override
    public Player basedOnStrikeRate(Team team) {
        team.getPlayers().sort(Comparator.comparingInt(Player::getStrikeRate));
        return team.getPlayers().get(0);
    }
}
