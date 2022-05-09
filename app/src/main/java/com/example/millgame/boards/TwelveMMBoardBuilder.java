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
        origin = boardPanel.getOrigin();
        board.setBoardPanel(boardPanel);
        board.setOrigin(origin);
    }

    public TwelveMMBoard build() {
        reset();
        buildPanel();
        return board;
    }
};