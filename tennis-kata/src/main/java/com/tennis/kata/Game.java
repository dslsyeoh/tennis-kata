/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public class Game extends GameState
{
    public Game(TennisGame game)
    {
        super(game);
    }

    @Override
    public String getScore()
    {
        String result = "Game for " + game.getLeadingPlayerName();
        startNewGame();
        return result;
    }

    private void startNewGame()
    {
        game.resetScore();
    }
}
