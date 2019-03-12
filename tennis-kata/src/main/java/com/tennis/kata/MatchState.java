/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public abstract class MatchState
{
    public TennisGame game;

    public MatchState(TennisGame game)
    {
        this.game = game;
    }

    public abstract String getScore();
}
