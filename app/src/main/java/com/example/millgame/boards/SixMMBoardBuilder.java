package com.example.millgame.boards;

import com.example.millgame.Position;

public class SixMMBoardBuilder implements BoardBuilder {
    private SixMMBoard board;
    private Position origin;

    public SixMMBoard build() {
        reset();
        createPositions();
        board.setOrigin(origin);
        return board;
    }

    public void reset() {
        board = new SixMMBoard();
    }

    public void createPositions() {
        // DEFINIR POSITIONS
    }
}