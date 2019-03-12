/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public class GameDetails
{
    private Player player;
    private int score;

    public GameDetails(Player player)
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void winGame()
    {
        score++;
    }
}
