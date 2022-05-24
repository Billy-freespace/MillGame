package com.example.millgame.boards;


import com.example.millgame.misc.Color;
import java.util.List;

public class ThreeMMBoard extends BoardWithDiagonals {
    // COPIED FROM SixMMBoard (UPDATE)
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'e';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 5;


    public ThreeMMBoard(List<Color> playerColors) {
        super(BoardVariant.THREE_MEN_MORRIS, playerColors);
    }


    public char maxXLabel(){ return MAX_XLABEL; }
    public char minXLabel(){ return MIN_XLABEL; }
    public int maxYLabel(){ return MAX_YLABEL; }
    public int minYLabel(){ return MIN_YLABEL; }
}
