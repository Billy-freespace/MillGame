package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.*;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlayerOperationTest {
    private NineMMBoard board;
    private Player player;
    private Player player2;

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

//    Test for AC6.1
    @Test
    public void movePieceTest() throws RankedException {
        Position origin = board.getOrigin();
        Position position = board.getPosition('a', 4);
        player.placePiece(position);
        player.movePiece(player.getPiece(position.getXLabel(), (char) position.getYLabel()), origin);

        assertEquals(player.getPiece(origin.getXLabel(), (char) origin.getYLabel()), board.getOrigin().getPiece());
        assertNull(position.getPiece());
    }

//    Test for AC6.3
    @Test
    public void movePieceNoEmptyPositionTest() throws InvalidPositionCoordinate, NotEmptyPosition, NoPiecesError {
        Position origin = board.getOrigin();
        Position position = board.getPosition('a', 4);
        player.placePiece(position);
        player.placePiece(origin);

        NotEmptyPosition thrown = assertThrows(NotEmptyPosition.class,
                () -> player.movePiece(player.getPiece(position.getXLabel(), (char) position.getYLabel()), origin)
        );
        assertEquals(NotEmptyPosition.getErrorMessage(origin.getXLabel(), origin.getYLabel()), thrown.getMessage());
    }

//    Test for AC6.5
    @Test
    public void movePieceInvalidPositionTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        Position origin = board.getOrigin();
        Position position = new Position('z', 100);
        player.placePiece(origin);

        InvalidPositionCoordinate thrown = assertThrows(InvalidPositionCoordinate.class,
                () -> player.placePiece(position)
        );
        assertEquals(InvalidPositionCoordinate.getErrorMessage(position.getXLabel(), position.getYLabel()), thrown.getMessage());
    }

//    Test for AC6.6
    @Test
    public void movePieceNotOwnPieceTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        player2 = PlayerFactory.create(PlayerType.HUMAN, PieceColor.BLACK, board);
        Position origin = board.getOrigin();
        player.placePiece(origin);

        NotOwnPiece thrown = assertThrows(NotOwnPiece.class,
                () -> player2.movePiece(origin.getPiece(), 'a', 4)
        );
        assertEquals(NotOwnPiece.getErrorMessage(origin.getPiece()), thrown.getMessage());
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
