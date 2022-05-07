package com.example.millgame.boards;

import com.example.millgame.Position;

public class NineMMBoardBuilder implements BoardBuilder {
    private NineMMBoard board;
    private Position origin;

    public void reset() {
        board = new NineMMBoard();
    }

    public void buildPanel(){
        NineMMBoardPanel boardPanel = new NineMMBoardPanel();
        Position origin = boardPanel.getOrigin();
        board.setBoardPanel(boardPanel);
        board.setOrigin(origin);
    }

    public NineMMBoard build() {
        reset();
        buildPanel();
        return board;
    }
}