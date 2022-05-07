package com.example.millgame.boards;

import com.example.millgame.Position;

public class TwelveMMBoardBuilder implements BoardBuilder {
    private TwelveMMBoard board;
    private Position origin;

    public void reset() {
        board = new TwelveMMBoard();
    }

    public void buildPanel(){
        TwelveMMBoardPanel boardPanel = new TwelveMMBoardPanel();
        board.setBoardPanel(boardPanel);
    }

    public TwelveMMBoard build() {
        reset();
        buildPanel();
        return board;
    }
};