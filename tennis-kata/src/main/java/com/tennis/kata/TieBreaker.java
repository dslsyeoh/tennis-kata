/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

import java.util.HashMap;
import java.util.Map;

public class TieBreaker extends SetState
{
    public TieBreaker(TennisGame tennisGame)
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
            put(0, "Zero");
            put(1, "One");
            put(2, "Two");
            put(3, "Three");
            put(4, "Four");
            put(5, "Five");
            put(6, "Six");
            put(7, "Seven");
            put(8, "Eight");
            put(9, "Nine");
            put(10, "Ten");
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
