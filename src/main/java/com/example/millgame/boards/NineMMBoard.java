package com.example.millgame.boards;


import com.example.millgame.MillGame.GameVariant;


public class NineMMBoard extends BoardWithoutDiagonals {
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'g';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 7;

    public NineMMBoard() { super(BoardVariant.NINE_MEN_MORRIS); }

    public char maxXLabel(){ return MAX_XLABEL; }
    public char minXLabel(){ return MIN_XLABEL; }
    public int maxYLabel(){ return MAX_YLABEL; }
    public int minYLabel(){ return MIN_YLABEL; }
}
