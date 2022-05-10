package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NineMMBoardBuildTest {

    @Test
    public void createBoardTest(){
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        int NINE_MEM_MORRIS_PIECES = 24;

        char originXLabel = 'a';
        int originYLabel = 1;

        NineMMBoard board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);

        assertEquals(variant, board.getVariant());
        assertEquals(NINE_MEM_MORRIS_PIECES, board.countPositions());

        Position origin = board.getOrigin();

        assertEquals(originXLabel, origin.getXLabel());
        assertEquals(originYLabel, origin.getYLabel());
    }
}