package com.example.millgame;

import com.example.millgame.boards.NineMMBoard;

import com.example.millgame.exceptions.RankedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//@Disabled("PlayerFactory.create(PlayerType, Color, Board) method was delete - UPDATE")
public class NineMMBoardBuildTest {

    private MillGame game;
    private NineMMBoard board;

    @BeforeEach
    public void initBoard() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;

        game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();

        board = (NineMMBoard) game.getBoard();
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

//    @Disabled("Refactor test - BoardVariant enumeration was added")
    @Test
    public void boardVariantTest() {
        Board.BoardVariant variant = Board.BoardVariant.NINE_MEN_MORRIS;

        assertEquals(variant, board.getVariant());
    }

//    @Disabled("Board.number.PlayerPiecesTest was deleted (this method was moved to MillGame class) - UPDATE TEST")
    @Test
    public void numberPlayerPiecesTest(){
        int playerPieces = 9;

        assertEquals(playerPieces, game.getNumberPlayerPieces());
    }
}