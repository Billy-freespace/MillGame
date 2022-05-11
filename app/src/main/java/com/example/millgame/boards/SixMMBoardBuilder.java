package com.example.millgame.boards;

import com.example.millgame.Position;

public class SixMMBoardBuilder implements BoardBuilder {
    private SixMMBoard board;
    private Position origin;

    public void reset() {
        board = new SixMMBoard();
    }

    public void buildPanel(){
        SixMMBoardPanel boardPanel = new SixMMBoardPanel();
        origin = boardPanel.getOrigin();
        board.setBoardPanel(boardPanel);
        board.setOrigin(origin);
    }

    public SixMMBoard build() {
        reset();
        buildPanel();
        return board;
    }
}