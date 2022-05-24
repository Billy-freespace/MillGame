package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.boards.TwelveMMBoard;

import com.example.millgame.misc.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TwelveMMBoardBuildTest {

    private static TwelveMMBoard board;

    @BeforeAll
    public static void initBoard(){
        List<Color> playerColors = new ArrayList<Color>();
        playerColors.add(Color.WHITE);
        playerColors.add(Color.BLACK);

        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;
        board = (TwelveMMBoard) BoardCreatorDirector.makeMMBoard(variant, playerColors);
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

    @Disabled("Refactor test - BoardVariant enumeration was added")
    @Test
    public void boardVariantTest() {
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;

        assertEquals(variant, board.getVariant());
    }
}