package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.TwelveMMBoard;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TwelveMMBoardBuildTest {

    private MillGame game;
    private TwelveMMBoard board;

    @BeforeEach
    public void initBoard() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;

        game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();

        board = (TwelveMMBoard) game.getBoard();
    }

    @Test
    public void numberPositionsTest() {
        int numberPositions = 24;

        assertEquals(numberPositions, board.countPositions());
    }

    @Test
    public void originPositionTest() {
        char xLabel = 'a';
        int yLabel = 1;
        Position origin = board.getOrigin();

        assertEquals(xLabel, origin.getXLabel());
        assertEquals(yLabel, origin.getYLabel());
    }

//    @Disabled("Refactor test - BoardVariant enumeration was added")
    @Test
    public void boardVariantTest() {
        Board.BoardVariant variant = Board.BoardVariant.TWELVE_MEN_MORRIS;

        assertEquals(variant, board.getBoardVariant());
    }
}
