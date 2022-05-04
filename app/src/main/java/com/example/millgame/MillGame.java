package com.example.millgame;

import com.example.millgame.logging.GameLogger;

import java.util.ArrayList;

public class MillGame {
    private TurnIterator turniter;
    private ArrayList<Player> players;
    private Board board;
    private GameLogger logger;

    public MillGame(){ // useless constructor, to create a MillGame object use MillGameBuilder class
        turniter = null;
        players = null;
        board = null;

        // INITIALIZING GameLogger
    }
    public Player nextTurn(){ return turniter.next(); }
    public Player getActivePlayer(){
        return turniter.getIterationState();
    }
    public boolean isGameOver(){ return false; }


    public void setTurnIterator(TurnIterator itr){ turniter = itr; }
    public void setPlayers(ArrayList<Player> players){ this.players = players; }
    public void setBoard(Board board){ this.board = board; }

    /*
     * Inner enumerations
     */

    public enum GameMode {
        HUMAN_HUMAN,
        HUMAN_ROBOT
    }
    public enum GameVariant {
        THREE_MEN_MORRIS,
        SIX_MEN_MORRIS,
        NINE_MEN_MORRIS,
        TWELVE_MEN_MORRIS
    }

    public enum GameStage {
        UNINITIATED,
        POSITIONING,
        PLAYING
    }
}
