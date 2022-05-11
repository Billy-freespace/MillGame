package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NineMMBoardBuildTest {
    private final MillGame.GameVariant nineMMGameVariant = MillGame.GameVariant.NINE_MEN_MORRIS;
    private  NineMMBoard nineMMBoard;

//    @Test
//    public void createBoardTest(){
//        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
//        int NINE_MEM_MORRIS_PIECES = 24;
//
//        char originXLabel = 'a';
//        int originYLabel = 1;
//
//        NineMMBoard board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);
//
//        assertEquals(variant, board.getGameVariant());
//        assertEquals(NINE_MEM_MORRIS_PIECES, board.countPositions());
//
//        Position origin = board.getOrigin();
//
//        assertEquals(originXLabel, origin.getXLabel());
//        assertEquals(originYLabel, origin.getYLabel());
//    }

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
        assertEquals(nineMMGameVariant, nineMMBoard.getGameVariant());
    }
}