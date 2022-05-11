package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NineMMBoardBuildTest {
    private final MillGame.GameVariant nineMMGameVariant = MillGame.GameVariant.NINE_MEN_MORRIS;
    private  NineMMBoard nineMMBoard;

    @BeforeEach
    public void createNineMMBoard(){
        nineMMBoard = (NineMMBoard) BoardCreatorDirector.makeMMBoard(nineMMGameVariant);
    }

    @Test
    public void numberPositionsTest() {
        int nineMMPiecesNumber = 24;
        assertEquals(nineMMPiecesNumber, nineMMBoard.countPositions());
    }

    @Test
    public void originPositionTest() {
        Position origin = new Position('a', 1);
        assertEquals(origin.getXLabel(), nineMMBoard.getOrigin().getXLabel());
        assertEquals(origin.getYLabel(), nineMMBoard.getOrigin().getYLabel());
    }

    @Test
    public void boardVariantTest() {
        assertEquals(nineMMGameVariant, nineMMBoard.getVariant());
    }
}