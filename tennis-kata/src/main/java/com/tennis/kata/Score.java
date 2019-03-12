/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

public interface Score
{
    void serverScores();
    void receiverScores();
    void serverGameScores();
    void receiverGameScores();
    void updateGameScores();
    void updateSetScores();
    void serverSetScore();
    void receiverSetScore();
}
