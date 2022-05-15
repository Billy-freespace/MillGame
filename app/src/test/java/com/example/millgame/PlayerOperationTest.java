package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoEmptyPosition;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PlayerOperationTest {
    private NineMMBoard board;
    private Player player;

    @BeforeEach
    public void createPlayers() {
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);
        player = PlayerFactory.create(PlayerType.HUMAN, PieceColor.WHITE, board);
    }

    //PLACE TESTS
    @Test
    public void placePieceTest() throws NoEmptyPosition, InvalidPositionCoordinate{
        /*
         * Test for AC3.1
         */
        Position origin = board.getOrigin();
        Piece positionPiece = origin.getPiece();

        //CHECK  EMPTY POSITION
        assertEquals(null, positionPiece);

        try {

            player.placePiece(origin.getXLabel(), origin.getYLabel());

        } catch (NoPiecesError e){
            // DONOTHING
        }

        //CHECK IF NO EMPTY POSITION
        positionPiece = origin.getPiece();
        assertNotEquals(null, positionPiece);

        //CHECK POSITION PIECE
        assertEquals(origin, positionPiece.getPosition());
    }

    @Test
    public void placePieceOnOccupiedPositionTest() throws InvalidPositionCoordinate{
        /*
         * Test for AC3.2
         */
        try {
            player.placePiece('a', 7);
            player.placePiece('b', 6);
        } catch (NoPiecesError | NoEmptyPosition e){
            // DONOTHING
        }

        assertThrows(NoEmptyPosition.class, () -> {
            player.placePiece('a', 7);
        });
    }

    @Test
    public void placePieceOnInvalidPositionTest() throws NoEmptyPosition, InvalidPositionCoordinate{
        /*
         * Test for AC3.3
         */
        char xLabel = 'z';
        int yLabel = 100;

        assertThrows(InvalidPositionCoordinate.class, () -> {
            player.placePiece(xLabel, yLabel);
        });

    }

    @Test
    public void noPositioningPiecesTest() throws NoEmptyPosition, InvalidPositionCoordinate{
        /*
         * Test for AC3.4
         */
        int j = 1;
        try {
            // 3 fichas
            for (char i = 'a'; i <= 'g';  i++, j++) {
                if (i == 'd') continue;
                player.placePiece(i, j);
            }

            player.placePiece('a', 7);
            player.placePiece('b', 6);
            player.placePiece('c', 5);
        } catch (NoPiecesError e){
            // DONOTHING
        }

        assertThrows(NoPiecesError.class, () -> {
            player.placePiece('e', 3);;
        });
    }

    //MOVE TESTS
    @Test
    public void movePieceTets() throws RankedException {
        Position origin = board.getOrigin();
        //FINAL POSITION
        char xLabel = 'a'; 
        int yLabel = 4;
        Position posFinal = board.getPosition(xLabel, yLabel);

        player.placePiece(origin.getXLabel(), origin.getYLabel());

        Piece positionPiece = origin.getPiece();
        //CHECK 
        assertNotEquals(null, positionPiece);
        assertEquals(origin, positionPiece.getPosition());
        
        player.movePiece(positionPiece, xLabel, yLabel);
        
        Piece originPiece = origin.getPiece();
        assertEquals(null, originPiece);
        assertNotEquals(null, posFinal.getPiece());
        assertEquals(posFinal, posFinal.getPiece().getPosition());

        //assertNotEquals(origin, positionPiece.getPosition());
    }


    /*
    @Test
    public void testEmptyPosition() throws RankedException {

        Position origin = board.getOrigin();;
        Piece positionPiece = origin.getPiece();

        //CHECK  EMPTY POSITION
        assertEquals(null, positionPiece);

        try {

            player1.placePiece(origin.getXLabel(), origin.getYLabel());

        } catch (NoPiecesError e){
            // DONOTHING
        }

        //CHECK NO EMPTY POSITION
        positionPiece = origin.getPiece();
        assertNotEquals(null, positionPiece);

        //CHECK POSITION PIECE
        assertEquals(origin, positionPiece.getPosition());

    }

    @Test
    public void testInvalidNoPieces() throws RankedException{
        //Player player1 = PlayerFactory.create(PlayerType.HUMAN, PieceColor.WHITE, board);
        int j = 1;
        try {
            // 3 fichas
            for (char i = 'a'; i <= 'g';  i++, j++) {
                if (i == 'd') continue;
                player1.placePiece(i, j);
            }

            player1.placePiece('a', 7);
            player1.placePiece('b', 6);
            player1.placePiece('c', 5);
        } catch (NoPiecesError e){
            // DONOTHING
        }

        assertThrows(NoPiecesError.class, () -> {
            player1.placePiece('e', 3);;
        });

    }

    @Test
    public void getInvalidPositionTest() {
        char xLabel = 'z';
        int yLabel = 100;

        assertThrows(InvalidPositionCoordinate.class, () -> {
            player2.placePiece(xLabel, yLabel);
        });
    }

    @Test
    public void getNoEmptyPositionTest() throws NoEmptyPosition, InvalidPositionCoordinate  {
        try {
            player2.placePiece('a', 7);
            player2.placePiece('b', 6);
        } catch (NoPiecesError | NoEmptyPosition e){
            // DONOTHING
        }

        assertThrows(NoEmptyPosition.class, () -> {
            player2.placePiece('a', 7);
        });
    }
     */
}
