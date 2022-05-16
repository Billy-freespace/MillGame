package com.example.millgame.boards;


import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Position;

import java.util.ArrayList;

public class SixMMBoard extends BoardWithoutDiagonals {
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'e';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 5;

    public static final int NUMBER_PIECES = 6;

    public SixMMBoard() {
        super(GameVariant.NINE_MEN_MORRIS);
    }

    public ArrayList<Position> getPossibleMovements(char xLabel, int yLabel){
        return null;
    }

    public boolean isValidMill(Mill mill) {
        return true;
    }

    public int getNumberPlayerPieces(){ return NUMBER_PIECES; }

    public char maxXLabel(){ return MAX_XLABEL; }
    public char minXLabel(){ return MIN_XLABEL; }
    public int maxYLabel(){ return MAX_YLABEL; }
    public int minYLabel(){ return MIN_YLABEL; }
}
