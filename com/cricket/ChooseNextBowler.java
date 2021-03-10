package com.cricket;

import java.util.Random;

public class ChooseNextBowler implements nextPlayer{

    @Override
    public Bowler nextSequential(Team team, int next) {
        return null;
    }

    @Override
    public Bowler RandomNext(Team team) {
        return team.getBowlers().get(new Random().nextInt(team.getBowlers().size()));
    }

    @Override
    public <T> T basedOnStrikeRate(Team team) {
        return null;
    }
}
