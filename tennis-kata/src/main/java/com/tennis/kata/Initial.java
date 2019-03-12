/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

import java.util.HashMap;
import java.util.Map;

public class Initial extends GameState
{
    public Initial(TennisGame tennisGame)
    {
        super(tennisGame);
    }

    @Override
    public String getScore()
    {
       return game.isTie() ? sameScore() : normalScore();
    }

    private Map<Integer, String> SCORES = new HashMap<Integer, String>()
    {
        {
            put(0, "Love");
            put(1, "Fifteen");
            put(2, "Thirty");
            put(3, "Forty");
        }
    };

    private String sameScore()
    {
        return String.format("%s-All", lookup(game.getServer().getScore()));
    }

    private String normalScore()
    {
        return String.format("%s-%s", lookup(game.getServer().getScore()), lookup(game.getReceiver().getScore()));
    }

    private String lookup(int score)
    {
        return SCORES.get(score);
    }
}
