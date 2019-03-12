/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public class Set extends SetState
{
    public Set(TennisGame tennisGame)
    {
        super(tennisGame);
    }

    @Override
    public String getScore()
    {
        String result = "Set for " + game.getSetPlayerName();
        startNewSet();
        return result;
    }

    private void startNewSet()
    {
        game.resetScore();
        game.resetGameScore();
        game.setSetState(null); // reset set state to non set
    }
}
