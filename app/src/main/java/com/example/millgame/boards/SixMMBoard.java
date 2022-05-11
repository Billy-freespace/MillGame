package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.Mill;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class SixMMBoard extends Board {
    public SixMMBoard() {
        super(GameVariant.NINE_MEN_MORRIS);
    }

    public ArrayList<Position> getPossibleMovements(char xLabel, int yLabel){
        return null;
    }

    @Override
    public boolean isValidMill(Mill mill) {
        return true;
    }
}
