/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public class Deuce extends GameState
{
    public Deuce(TennisGame tennisGame)
    {
        super(tennisGame);
    }

    @Override
    public String getScore()
    {
        return "Deuce";
    }
}
