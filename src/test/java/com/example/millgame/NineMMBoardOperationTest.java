package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.EmptyPositionError;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.Color;
import com.example.millgame.pieces.PieceFactory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NineMMBoardOperationTest {
    private  NineMMBoard board;

    @BeforeEach
    public void createNineMMBoard() {
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);
    }

    @Test
    public void placePieceTest() throws InvalidPositionCoordinate, NotEmptyPosition {
        /*
         * Test for AC1.2
         */

        char xLabel = 'a';
        int yLabel = 1;

        Piece piece = PieceFactory.create(Color.WHITE);

        board.placePiece(piece, xLabel, yLabel);
        Position origin = board.getOrigin();

        assertEquals(piece, origin.getPiece());
    }

    @Test
    public void placePieceOnInvalidPositionTest(){
        /*
         * Test for AC1.3
         */
    }

    @Test
    public void getPieceTest() throws InvalidPositionCoordinate, NotEmptyPosition {
        /*
         * Test for AC1.4
         */

        char xLabel = 'a';
        int yLabel = 1;

        Piece piece = PieceFactory.create(Color.WHITE);
        board.placePiece(piece, xLabel, yLabel);
        Position origin = board.getOrigin();

        assertEquals(piece, origin.getPiece());
    }

    @Test
    public void getPieceInvalidPositionTest(){
        /*
         * Test for AC1.5
         */

        char xLabel = 'a';
        int yLabel = -1;

        InvalidPositionCoordinate thrown = assertThrows(
                InvalidPositionCoordinate.class,
                () -> board.getPosition(xLabel, yLabel));
        assertEquals(InvalidPositionCoordinate.getErrorMessage(xLabel, yLabel), thrown.getMessage());
    }


    @Test
    public void removePieceTest() throws RankedException {
        /*
         * Test for AC1.6
         */

        char xLabel = 'a';
        int yLabel = 1;

        // place a piece on origin position
        Piece piece = PieceFactory.create(Color.WHITE);
        board.placePiece(piece, xLabel, yLabel);
        Position origin = board.getOrigin();
        assertEquals(piece, origin.getPiece());

        // remove placed piece on origin position
        board.removePiece(xLabel, yLabel);
        origin = board.getOrigin();

        assertNull(origin.getPiece());
        assertNull(piece.getPosition());
    }

    @Test
    public void removePieceEmptyPositionTest(){
        /*
         * Test for AC1.7
         */

        char xLabel = 'a';
        int yLabel = 1;

        EmptyPositionError thrown = assertThrows(
                EmptyPositionError.class,
                () -> board.removePiece(xLabel, yLabel));
        assertEquals(EmptyPositionError.getErrorMessage(xLabel, yLabel), thrown.getMessage());
    }

    @Test
    public void removePieceInvalidPositionTest(){
        /*
         * Test for AC1.8
         */

        char xLabel = 'a';
        int yLabel = -1;

        InvalidPositionCoordinate thrown = assertThrows(
                InvalidPositionCoordinate.class,
                () -> board.removePiece(xLabel, yLabel));
        assertEquals(InvalidPositionCoordinate.getErrorMessage(xLabel, yLabel), thrown.getMessage());
    }
}