/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public class Advantage extends GameState
{
    public Advantage(TennisGame game)
    {
        super(game);
    }

    @Override
    public String getScore()
    {
        return "Advantage for " + game.getLeadingPlayerName();
    }
}
