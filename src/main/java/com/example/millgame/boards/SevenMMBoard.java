package com.example.millgame.boards;


import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Position;
import com.example.millgame.misc.Color;

import java.util.ArrayList;
import java.util.List;

public class SevenMMBoard extends BoardWithoutDiagonals {
    // COPIED FROM SixMMBoard (UPDATE)
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'e';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 5;


    public SevenMMBoard(List<Color> playerColors) {
        super(BoardVariant.SEVEN_MEN_MORRIS, playerColors);
    }


    public char maxXLabel(){ return MAX_XLABEL; }
    public char minXLabel(){ return MIN_XLABEL; }
    public int maxYLabel(){ return MAX_YLABEL; }
    public int minYLabel(){ return MIN_YLABEL; }
}
