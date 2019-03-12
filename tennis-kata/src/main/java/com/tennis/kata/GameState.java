/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public abstract class GameState
{
    public TennisGame game;

    public GameState(TennisGame game)
    {
        this.game = game;
    }

    public abstract String getScore();
}
