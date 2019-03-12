/*
 * Written by Steven Yeoh
 *
 * Copyright (c) 2019.
 */

package com.tennis.kata;

import java.util.ArrayList;

public class TennisGame implements Score
{
    private Player server;
    private Player receiver;

    private GameDetails serverGameDetails;
    private GameDetails receiverGameDetails;
    private GameState gameState;

    private SetDetails setDetails;
    private SetState setState;

    private MatchDetails matchDetails;
    private MatchState matchState;

    private Initial INITIAL = new Initial(this);
    private Deuce DEUCE = new Deuce(this);
    private Advantage ADVANTAGE = new Advantage(this);
    private Game GAME = new Game(this);

    private Set SET = new Set(this);
    private TieBreaker TIE_BREAKER = new TieBreaker(this);

    private Match END = new Match(this);

    public TennisGame(String serverName, String receiverName)
    {
        server = new Player(serverName);
        receiver = new Player(receiverName);
        serverGameDetails = new GameDetails(server);
        receiverGameDetails = new GameDetails(receiver);
        setDetails = new SetDetails(new ArrayList<>());
        matchDetails = new MatchDetails(new ArrayList<>());

        gameState = INITIAL;
    }

    private void nextState()
    {
        if(isMatchEnded()) matchState = END;
        else if(isSet()) setState = SET;
        else if(isTieBreaker()) setState = TIE_BREAKER;
        else if(isGame()) gameState = GAME;
        else if(isDeuce()) gameState = DEUCE;
        else if(isAdvantage()) gameState = ADVANTAGE;
    }

    private void ensureMatchEnded()
    {
        if(isMatchEnded()) throw new IllegalStateException("Match Ended");
    }

    private void isNewGame()
    {
        if(isTieBreaker())
        {
            if(isTieBreakerGame()) winGame();
        }
        else if(isGame()) winGame();
        isNewSet(); // check game is new set if yes start new set with new game and update scores
    }

    private void isNewSet()
    {
        if(isSet())
        {
            if(isServerSet()) setDetails.serverWinSet();
            else setDetails.receiverWinSet();
            updateScores();
        }
    }

    private void updateScores()
    {
        updateGameScores();
        updateSetScores();
    }

    public void resetScore()
    {
        server.setScore(0);
        receiver.setScore(0);
    }

    public void resetGameScore()
    {
        serverGameDetails.setScore(0);
        receiverGameDetails.setScore(0);
    }

    // GAME STATE
    public boolean isTie()
    {
        return server.getScore() == receiver.getScore();
    }

    private boolean isDeuce()
    {
        return isTie() && server.getScore() >= 3;
    }

    private boolean isReadyToGame()
    {
        return server.getScore() > 3 || receiver.getScore() > 3;
    }

    private boolean isAdvantage()
    {
        return isReadyToGame() && Math.abs(server.getScore() - receiver.getScore()) == 1;
    }

    public String getLeadingPlayerName()
    {
        return server.getScore() > receiver.getScore() ? server.getName() : receiver.getName();
    }

    private boolean isGame()
    {
        return isReadyToGame() && Math.abs(server.getScore() - receiver.getScore()) >= 2;
    }

    public boolean isServerGame()
    {
        return server.getScore() > receiver.getScore();
    }

    // SET STATE
    private boolean isSet()
    {
        return (isReadyToSet() && Math.abs(serverGameDetails.getScore() - receiverGameDetails.getScore()) >= 2) || isTieBreakerGame();
    }

    private boolean isTieBreaker()
    {
        return serverGameDetails.getScore() == 6 && receiverGameDetails.getScore() == 6;
    }

    private boolean isReadyToGameTieBreaker()
    {
        return server.getScore() > 6 || receiver.getScore() > 6;
    }

    private boolean isTieBreakerGame()
    {
        return isReadyToGameTieBreaker() && Math.abs(server.getScore() - receiver.getScore()) >= 2;
    }

    private boolean isReadyToSet()
    {
        return serverGameDetails.getScore() >= 6 || receiverGameDetails.getScore() >= 6;
    }

    private boolean isServerSet()
    {
        return serverGameDetails.getScore() > receiverGameDetails.getScore();
    }

    public String getSetPlayerName()
    {
        if(isWinSetFromGame()) return serverGameDetails.getScore() > receiverGameDetails.getScore() ? server.getName() : receiver.getName();
        else return setDetails.getServerSetScore() > setDetails.getReceiverSetScore() ? server.getName() : receiver.getName();
    }

    public boolean isWinSetFromGame()
    {
        return serverGameDetails.getScore() > 0 && receiverGameDetails.getScore() > 0;
    }

    // END STATE
    private boolean isMatchEnded()
    {
        return setDetails.getServerSetScore() == 3 || setDetails.getReceiverSetScore() == 3;
    }

    public String getWinnerName()
    {
        return setDetails.getServerSetScore() == 3 ? server.getName() : receiver.getName();
    }

    @Override
    public void serverScores()
    {
        ensureMatchEnded();
        server.winBall();
        isNewGame();
        nextState();
    }

    @Override
    public void receiverScores()
    {
        ensureMatchEnded();
        receiver.winBall();
        isNewGame();
        nextState();
    }

    @Override
    public void serverGameScores()
    {
        ensureMatchEnded();
        serverGameDetails.winGame();
        isNewSet();
        nextState();
    }

    @Override
    public void receiverGameScores()
    {
        ensureMatchEnded();
        receiverGameDetails.winGame();
        isNewSet();
        nextState();
    }

    @Override
    public void serverSetScore()
    {
        ensureMatchEnded();
        setDetails.serverWinSet();
        updateSetScores();

        updateState();
    }

    @Override
    public void receiverSetScore()
    {
        ensureMatchEnded();
        setDetails.receiverWinSet();
        updateSetScores();

        updateState();
    }

    @Override
    public void updateGameScores()
    {
        setDetails.update(new Integer[]{ serverGameDetails.getScore(), receiverGameDetails.getScore() });
    }

    @Override
    public void updateSetScores()
    {
        matchDetails.update(new Integer[] { setDetails.getServerSetScore(), setDetails.getReceiverSetScore()});
    }

    private void updateState()
    {
        setState = SET;
        if(isMatchEnded())
        {
            matchState = END;
            setState = null;
        }
    }

    private void winGame()
    {
        (isServerGame() ? serverGameDetails : receiverGameDetails).winGame();
    }

    public Player getServer()
    {
        return server;
    }

    public Player getReceiver()
    {
        return receiver;
    }

    public SetDetails getSetDetails()
    {
        return setDetails;
    }

    public void setSetState(SetState setState)
    {
        this.setState = setState;
    }


    public String getScore()
    {
        if(matchState != null) return matchState.getScore();
        else if(setState != null) return setState.getScore();
        return gameState.getScore();
    }
}
