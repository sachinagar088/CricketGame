package com.cricket;

public interface nextPlayer {
    <T> T nextSequential(Team team, int next);
    <T> T RandomNext(Team team);
    <T> T basedOnStrikeRate(Team team);
}
