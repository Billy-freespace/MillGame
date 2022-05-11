    package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.pieces.PieceFactory;
import com.example.millgame.players.HumanPlayerFactory;

import static org.junit.jupiter.api.Assertions.*;

import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NineMMBoardOperationTest {
    private final MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
    private  NineMMBoard board;
    private Player player2;

    @BeforeEach
    public void createNineMMBoard(){
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);
        player2 = new HumanPlayerFactory().create(PieceColor.BLACK, board);
        System.out.println(player2);
    }

    @Test
    public void getPositionTest() throws InvalidPositionCoordinate {
        char originXLabel = 'a';
        int originYLabel = 1;

        Position origin = board.getOrigin();
        Position originPosition = board.getPosition(originXLabel, originYLabel);

        assertEquals(origin, originPosition);
    }

    @Test
    public void getInvalidPositionTest(){
        char xLabel = 'c';
        int yLabel = 1;

        assertThrows(InvalidPositionCoordinate.class, () -> {
            board.getPosition(xLabel, yLabel);
        });
    }

    @Nested
    class PlayerOperationTest { 
        
    }

    ////
    /*
    @Test
    public void placePieceTest() throws InvalidPositionCoordinate{
        char xLabel = 'c';
        int yLabel = 3;

        Piece piece = PieceFactory.create(PieceColor.WHITE);

        Position c3 = board.getPosition(xLabel, yLabel);
        assertNull(c3.getPiece());

        board.placePiece(piece, xLabel, yLabel);
        c3 = board.getPosition(xLabel, yLabel);

        assertEquals(piece, c3.getPiece());
    }
     */

    @Test
    public void removePieceTest() throws InvalidPositionCoordinate{
        char xLabel = 'c';
        int yLabel = 3;

        Piece piece = PieceFactory.create(PieceColor.WHITE);
        board.placePiece(piece, xLabel, yLabel);
        Position c3 = board.getPosition(xLabel, yLabel);
        assertEquals(piece, c3.getPiece());

        board.removePiece(xLabel, yLabel);
        c3 = board.getPosition(xLabel, yLabel);
        assertNull(c3.getPiece());
    }
}