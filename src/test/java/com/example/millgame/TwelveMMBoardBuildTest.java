package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.TwelveMMBoard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwelveMMBoardBuildTest {

    private static TwelveMMBoard board;

    @BeforeAll
    public static void initBoard(){
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;
        board = (TwelveMMBoard) BoardCreatorDirector.makeMMBoard(variant);
    }

    @Test
    public void numberPositionsTest() {
        int numberPieces = 24;

        assertEquals(numberPieces, board.getNumberPositions());
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
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;

        assertEquals(variant, board.getVariant());
    }

    @Test
    public void numberPlayerPiecesTest(){
        int playerPieces = 12;

        assertEquals(playerPieces, board.getNumberPlayerPieces());
    }
}