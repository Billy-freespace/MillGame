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

    @Test
    public void placePieceTest(){
        /*
         * Test for AC3.1
         */
    }

    @Test
    public void placePieceOnOccupiedPositionTest(){
        /*
         * Test for AC3.2
         */
    }

    @Test
    public void placePieceOnInvalidPositionTest(){
        /*
         * Test for AC3.3
         */
    }

    @Test
    public void noPositioningPiecesTest(){
        /*
         * Test for AC3.4
         */
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
