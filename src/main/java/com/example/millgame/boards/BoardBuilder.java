package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.Position;
import com.example.millgame.misc.Color;

import java.util.List;

public abstract class BoardBuilder {
    protected Board board;
    protected Position origin;
    protected List<Color> playerColors;

    public abstract void reset();
    public abstract void createPositions();
    public Board build(){
        reset();
        createPositions();
        board.setOrigin(origin);
        return board;
    }
    public BoardBuilder setPlayerColors(List<Color> colors){
        playerColors = colors;
        return this;
    }
}
