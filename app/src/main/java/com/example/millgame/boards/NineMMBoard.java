package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.Mill;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class NineMMBoard extends Board {
    public static final int NUMBER_PIECES = 9;
    public NineMMBoard() {
        super(GameVariant.NINE_MEN_MORRIS);
    }

    public ArrayList<Position> getPossibleMovements(char xLabel, int yLabel){
        return null;
    }
    
    public boolean isValidMill(Mill mill) {
        return true;
    }

    public int getNumberPieces(){ return NUMBER_PIECES; }
}
