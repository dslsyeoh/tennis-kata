/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public class Match extends MatchState
{
    public Match(TennisGame tennisGame)
    {
        super(tennisGame);
    }

    @Override
    public String getScore()
    {
        return "Champion is " + game.getWinnerName();
    }
}
