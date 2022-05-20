package com.example.millgame.boards;


import com.example.millgame.MillGame.GameVariant;


public class NineMMBoard extends BoardWithoutDiagonals {
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'g';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 7;

    public static final int NUMBER_PIECES = 9;
    public NineMMBoard() { super(BoardVariant.NINE_MEN_MORRIS); }

    public int getNumberPlayerPieces(){ return NUMBER_PIECES; }

    public char maxXLabel(){ return MAX_XLABEL; }
    public char minXLabel(){ return MIN_XLABEL; }
    public int maxYLabel(){ return MAX_YLABEL; }
    public int minYLabel(){ return MIN_YLABEL; }
}
