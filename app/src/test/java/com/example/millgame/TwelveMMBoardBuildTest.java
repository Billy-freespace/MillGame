package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.TwelveMMBoard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwelveMMBoardBuildTest {

    @Test
    public void createBoardTest(){
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;
        int TWELVE_MEM_MORRIS_PIECES = 24;

        char originXLabel = 'a';
        int originYLabel = 1;

        TwelveMMBoard board = (TwelveMMBoard) BoardCreatorDirector.makeMMBoard(variant);

        assertEquals(variant, board.getGameVariant());
        assertEquals(TWELVE_MEM_MORRIS_PIECES, board.countPositions());

        Position origin = board.getOrigin();

        assertEquals(originXLabel, origin.getXLabel());
        assertEquals(originYLabel, origin.getYLabel());
    }
}