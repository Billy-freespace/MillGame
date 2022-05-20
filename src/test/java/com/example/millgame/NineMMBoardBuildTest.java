package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NineMMBoardBuildTest {

    private static NineMMBoard board;

    @BeforeAll
    public static void initBoard(){
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);
    }

    @Test
    public void numberPositionsTest() {
        int numberPieces = 24;

        assertEquals(numberPieces, board.countPositions());
    }

    @Test
    public void originPositionTest() {
        char xLabel = 'a';
        int yLabel = 1;

        Position origin = board.getOrigin();

        assertEquals(xLabel, origin.getXLabel());
        assertEquals(yLabel, origin.getYLabel());
    }

    @Test
    public void boardVariantTest() {
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;

        assertEquals(variant, board.getVariant());
    }

    @Test
    public void numberPlayerPiecesTest(){
        int playerPieces = 9;

        assertEquals(playerPieces, board.getNumberPlayerPieces());
    }
}