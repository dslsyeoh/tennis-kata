/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

import java.util.List;

public class SetDetails
{
    private List<Integer[]> scores;
    private int serverSetScore;
    private int receiverSetScore;

    public SetDetails(List<Integer[]> scores)
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

    public void serverWinSet()
    {
        serverSetScore++;
    }

    public void receiverWinSet()
    {
        receiverSetScore++;
    }

    public int getServerSetScore()
    {
        return serverSetScore;
    }

    public int getReceiverSetScore()
    {
        return receiverSetScore;
    }
}
