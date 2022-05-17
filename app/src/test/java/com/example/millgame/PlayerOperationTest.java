package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.InvalidMovement;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.pieces.PieceFactory;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
    public void placePieceTest() throws NotEmptyPosition, InvalidPositionCoordinate{
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
        } catch (NoPiecesError | NotEmptyPosition e){
            // DONOTHING
        }

        assertThrows(NotEmptyPosition.class, () -> {
            player.placePiece('a', 7);
        });
    }

    @Test
    public void placePieceOnInvalidPositionTest() throws NotEmptyPosition, InvalidPositionCoordinate{
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
    public void noPositioningPiecesTest() throws NotEmptyPosition, InvalidPositionCoordinate{
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

    @Test
    public void movePieceTest() throws RankedException {
        Position origin = board.getOrigin();
        Position position = board.getPosition('a', 4);
        player.placePiece(position);
        player.movePiece(player.getPiece(position.getXLabel(), (char) position.getYLabel()), origin);

        assertEquals(player.getPiece(origin.getXLabel(), (char) origin.getYLabel()), board.getOrigin().getPiece());
        assertNull(position.getPiece());
    }

    @Test
    public void movePieceNotNeighbourTest() throws RankedException {
        Position origin = board.getOrigin();
        char xLabel = 'a';
        int yLabel = 7;
        Position position = board.getPosition(xLabel, yLabel);
        player.placePiece(position);
        assertThrows(InvalidMovement.class, () -> {
            player.movePiece(player.getPiece(position.getXLabel(), (char) position.getYLabel()), origin);
        });
        assertNotNull(position.getPiece());
        assertEquals(player.getPiece(position.getXLabel(), (char) position.getYLabel()), board.getPosition(xLabel, yLabel).getPiece());
        assertNull(origin.getPiece());
    }

    /*@Test
    public void movePieceOnInvalidPositionTest() throws RankedException {
        Position origin = board.getOrigin();
        Position position = board.getPosition('a', 7);
        player.placePiece(position);
        assertThrows(InvalidMovement.class, () -> {
            player.movePiece(player.getPiece(position.getXLabel(), (char) position.getYLabel()), origin);
        });
    }*/

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
