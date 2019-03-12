/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

import java.util.List;

public class MatchDetails
{
    private List<Integer[]> scores;

    public MatchDetails(List<Integer[]> scores)
    {
        this.scores = scores;
    }

    public List<Integer[]> getScores()
    {
        return scores;
    }

    public void update(Integer[] score)
    {
        scores.add(score);
    }
}
