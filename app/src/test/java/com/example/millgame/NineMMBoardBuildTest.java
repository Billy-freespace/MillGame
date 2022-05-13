package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NineMMBoardBuildTest {
    private  NineMMBoard board;

    @BeforeEach
    public void createNineMMBoard(){
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(MillGame.GameVariant.NINE_MEN_MORRIS);
    }

    @Test
    public void numberPositionsTest() {
        int positions = 24;

        assertEquals(positions, board.countPositions());
    }

    @Test
    public void originPositionTest() {
        char xLabel = 'a';
        int yLabel = '1';

        Position origin = board.getOrigin();

        assertEquals(xLabel, origin.getXLabel());
        assertEquals(yLabel, origin.getYLabel());
    }

    @Test
    public void boardVariantTest() {
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;

        assertEquals(variant, board.getVariant());
    }
}