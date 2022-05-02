package com.example.millgame;

import java.util.ArrayList;

public class MillGame {
    public void setTurnIterator(TurnIterator itr){}
    public void setPlayers(ArrayList<Player> players){}
    public void setBoard(Board board){}

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
