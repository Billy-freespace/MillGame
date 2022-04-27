package com.example.millgame;

import java.util.ArrayList;

enum GameVariant {
    NINE_MEN_MORRIS
};

enum GameMode {
    HUMAN_HUMAN,
    HUMAN_ROBOT
};

public class MillGame {

    public void setTurnIterator(TurnIterator itr){}
    public void setPlayers(ArrayList<Player> players){}
    public void setBoard(Board board){}
}
