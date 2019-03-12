/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestTennisGame
{
    private TennisGame tennisGame;

    @Before
    public void startGame()
    {
        tennisGame = new TennisGame("King", "Mist");
    }

    @Test
    public void test_all_love()
    {
        assertEquals("Love-All", tennisGame.getScore());
    }

    @Test
    public void test_15_0()
    {
        givenScores(1, 0);
        assertEquals("Fifteen-Love", tennisGame.getScore());
    }

    @Test
    public void test_30_0()
    {
        givenScores(2, 0);
        assertEquals("Thirty-Love", tennisGame.getScore());

    }

    @Test
    public void test_40_0()
    {
        givenScores(3, 0);
        assertEquals("Forty-Love", tennisGame.getScore());

    }

    @Test
    public void test_0_15()
    {
        givenScores(0, 1);
        assertEquals("Love-Fifteen", tennisGame.getScore());
    }

    @Test
    public void test_0_30()
    {
        givenScores(0, 2);
        assertEquals("Love-Thirty", tennisGame.getScore());

    }

    @Test
    public void test_0_40()
    {
        givenScores(0, 3);
        assertEquals("Love-Forty", tennisGame.getScore());

    }

    @Test
    public void test_15_15()
    {
        givenScores(1, 1);
        assertEquals("Fifteen-All", tennisGame.getScore());
    }

    @Test
    public void test_30_15()
    {
        givenScores(2, 1);
        assertEquals("Thirty-Fifteen", tennisGame.getScore());
    }

    @Test
    public void test_15_30()
    {
        givenScores(1, 2);
        assertEquals("Fifteen-Thirty", tennisGame.getScore());
    }

    @Test
    public void test_deuce()
    {
        givenScores(3, 3);
        assertEquals("Deuce", tennisGame.getScore());
    }

    @Test
    public void test_deuce_after_advantage()
    {
        givenScores(5, 5);
        assertEquals("Deuce", tennisGame.getScore());
    }

    @Test
    public void test_server_advantage()
    {
        givenScores(4, 3);
        assertEquals("Advantage for King", tennisGame.getScore());
    }

    @Test
    public void test_receiver_advantage_after_deuce()
    {
        givenScores(4, 5);
        assertEquals("Advantage for Mist", tennisGame.getScore());
    }

    @Test
    public void test_server_win_game()
    {
        givenScores(4, 2);
        assertEquals("Game for King", tennisGame.getScore());
    }

    @Test
    public void test_receiver_win_game()
    {
        givenScores(2, 4);
        assertEquals("Game for Mist", tennisGame.getScore());
    }

    @Test
    public void test_server_win_game_after_advantage()
    {
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
    }


    @Test
    public void test_server_win_set()
    {
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        List<Integer[]> expected = new ArrayList<Integer[]>()
        {
            {
                add(new Integer[]{6, 0});
            }
        };

        IntStream.range(0, expected.size()).forEach(index ->
                assertArrayEquals(expected.get(index), tennisGame.getSetDetails().getScores().get(index)));
    }

    @Test
    public void test_server_win_2_set()
    {
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        assertEquals(1, tennisGame.getSetDetails().getScores().size());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Game for King", tennisGame.getScore());
        givenScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        assertEquals(2, tennisGame.getSetDetails().getScores().size());
    }

    @Test
    public void test_receiver_win_set()
    {
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
    }

    @Test
    public void test_receiver_win_2_set()
    {
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        assertEquals(2, tennisGame.getSetDetails().getScores().size());
    }

    @Test
    public void test_tie_breaker_1_0()
    {
        givenGameScores(6, 6);
        givenScores(1, 0);
        assertEquals("One-Zero", tennisGame.getScore());
    }

    @Test
    public void test_tie_breaker_1_1()
    {
        givenGameScores(6, 6);
        givenScores(1, 0);
        givenScores(0, 1);
        assertEquals("One-All", tennisGame.getScore());
    }

    @Test
    public void test_tie_breaker_6_1()
    {
        givenGameScores(6, 6);
        givenScores(6, 1);
        assertEquals("Six-One", tennisGame.getScore());
    }

    @Test
    public void test_tie_breaker_6_6()
    {
        givenGameScores(6, 6);
        givenScores(6, 6);
        assertEquals("Six-All", tennisGame.getScore());
    }

    @Test
    public void test_tie_breaker_7_6()
    {
        givenGameScores(6, 6);
        givenScores(7, 6);
        assertEquals("Seven-Six", tennisGame.getScore());
    }

    @Test
    public void test_tie_breaker_10_9()
    {
        givenGameScores(6, 6);
        givenScores(10, 9);
        assertEquals("Ten-Nine", tennisGame.getScore());
    }

    @Test
    public void test_server_win_set_10_8()
    {
        givenGameScores(6, 6);
        givenScores(7, 6);
        givenScores(0, 1);
        givenScores(1, 0);
        givenScores(0, 1);
        givenScores(1, 0);
        givenScores(1, 0);
        assertEquals("Set for King", tennisGame.getScore());
    }

    @Test
    public void test_receiver_win_set_6_8()
    {
        givenGameScores(6, 6);
        givenScores(6, 8);
        assertEquals("Set for Mist", tennisGame.getScore());
    }

    @Test
    public void test_server_win_set_tie_breaker()
    {
        givenGameScores(6, 6);
        givenScores(6, 1);
        givenScores(1, 0);
        assertEquals("Set for King", tennisGame.getScore());
    }

    @Test
    public void test_server_champion()
    {
        givenGameScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        givenGameScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        givenGameScores(6, 4);
        assertEquals("Champion is King", tennisGame.getScore());
    }

    @Test
    public void test_server_3_set_receiver_2_set_server_champion()
    {
        givenGameScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        givenGameScores(6, 4);
        assertEquals("Set for King", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(6, 4);
        assertEquals("Champion is King", tennisGame.getScore());
    }

    @Test
    public void test_receiver_champion()
    {
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Champion is Mist", tennisGame.getScore());
    }

    @Test
    public void test_server_3_sets_receiver_0_sets_server_champion()
    {
        givenSetScores(1, 0);
        assertEquals("Set for King", tennisGame.getScore());
        givenSetScores(1, 0);
        assertEquals("Set for King", tennisGame.getScore());
        givenSetScores(1, 0);
        assertEquals("Champion is King", tennisGame.getScore());
    }

    @Test
    public void test_server_2_sets_receiver_3_sets_receiver_champion()
    {
        givenSetScores(2, 3);
        assertEquals("Champion is Mist", tennisGame.getScore());
    }

    @Test(expected = IllegalStateException.class)
    public void test_match_ended_exception()
    {
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Set for Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Champion is Mist", tennisGame.getScore());
        givenGameScores(4, 6);
        assertEquals("Match Ended", tennisGame);
    }

    private void givenScores(int serverScore, int receiverScore)
    {
        IntStream.range(0, Math.max(serverScore, receiverScore)).forEach(i -> {
            if(i < serverScore)
            {
                tennisGame.serverScores();
            }
            if(i < receiverScore)
            {
                tennisGame.receiverScores();
            }
        });
    }

    private void givenGameScores(int serverScore, int receiverScore)
    {
        IntStream.range(0, Math.max(serverScore, receiverScore)).forEach(i -> {
            if(i < serverScore)
            {
                tennisGame.serverGameScores();
            }
            if(i < receiverScore)
            {
                tennisGame.receiverGameScores();
            }
        });
    }

    private void givenSetScores(int serverScore, int receiverScore)
    {
        IntStream.range(0, Math.max(serverScore, receiverScore)).forEach(i -> {
            if(i < serverScore)
            {
                tennisGame.serverSetScore();
            }
            if(i < receiverScore)
            {
                tennisGame.receiverSetScore();
            }
        });
    }
}
