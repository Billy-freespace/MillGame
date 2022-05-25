package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;

import com.example.millgame.misc.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("PlayerFactory.create(PlayerType, Color, Board) method was delete - UPDATE")
public class NineMMBoardBuildTest {

    private static NineMMBoard board;

    @BeforeAll
    public static void initBoard(){
        List<Color> playerColors = new ArrayList<Color>();
        playerColors.add(Color.WHITE);
        playerColors.add(Color.BLACK);

        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant, playerColors);
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
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;

        assertEquals(variant, board.getBoardVariant());
    }

    @Disabled("Board.number.PlayerPiecesTest was deleted (this method was moved to MillGame class) - UPDATE TEST")
    @Test
    public void numberPlayerPiecesTest(){
        int playerPieces = 9;

        //assertEquals(playerPieces, board.getNumberPlayerPieces());
    }
}