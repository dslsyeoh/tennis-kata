/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public abstract class SetState
{
    public TennisGame game;

    public SetState(TennisGame game)
    {
        this.game = game;
    }

    public abstract String getScore();
}
