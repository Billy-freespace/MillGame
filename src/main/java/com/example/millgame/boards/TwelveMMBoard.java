package com.example.millgame.boards;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.misc.Color;

import java.util.List;


public class TwelveMMBoard extends BoardWithDiagonals {

    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'g';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 7;

    public static final int NUMBER_PIECES = 12;
    public TwelveMMBoard(List<Color> playerColors) {
        super(BoardVariant.TWELVE_MEN_MORRIS, playerColors);
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