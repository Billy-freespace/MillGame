package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.*;
import com.example.millgame.misc.Color;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//@Disabled("PlayerFactory.create(PlayerType, Color, Board) method was delete - UPDATE")
class PlayerOperationTest {

    private MillGame game;
    private NineMMBoard board;
    private Player player;
    private Player player2;

    @BeforeEach
    public void createPlayers() throws RankedException {
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
        player = game.getActivePlayer();
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

//    Test for AC6.1
    @Test
    public void movePieceTest() throws RankedException {
        // place a4 (pieza) -> get pieza a4 -> mueve pieza al origin -> pieza == origin.getPiece()
        Position origin = board.getOrigin();
        Position position = board.getPosition('a', 4);
        player.placePiece(position);
        Piece piece = position.getPiece();

        player.movePiece(piece, origin);

        assertEquals(piece, origin.getPiece());
        assertNull(position.getPiece());
    }

//    Test for AC6.3
    @Test
    public void movePieceNoEmptyPositionTest() throws InvalidPositionCoordinate, NotEmptyPosition, NoPiecesError {
        Position origin = board.getOrigin();
        Position position = board.getPosition('a', 4);
        player.placePiece(position);
        Piece piece = position.getPiece();
        player.placePiece(origin);

        NotEmptyPosition thrown = assertThrows(NotEmptyPosition.class,
                () -> player.movePiece(piece, origin)
        );
        assertEquals(NotEmptyPosition.getErrorMessage(origin.getXLabel(), origin.getYLabel()), thrown.getMessage());
    }

//    Test for AC6.4
    @Test
    public void movePieceNotNeighbourTest() throws RankedException {
        Position origin = board.getOrigin();
        char xLabel = 'a';
        int yLabel = 7;
        Position position = board.getPosition(xLabel, yLabel);
        player.placePiece(position);
        Piece piece = position.getPiece();
        assertThrows(InvalidMovement.class, () -> {
            player.movePiece(piece, origin);
        });
        assertNotNull(position.getPiece());
        assertEquals(piece, board.getPosition(xLabel, yLabel).getPiece());
        assertNull(origin.getPiece());
    }

//    Test for AC6.5
    @Test
    public void movePieceInvalidPositionTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        Position origin = board.getOrigin();
        Position position = new Position('a', -1);
        player.placePiece(origin);
        Piece piece = origin.getPiece();

        InvalidPositionCoordinate thrown = assertThrows(InvalidPositionCoordinate.class,
                () -> player.movePiece(piece, position)
        );
        assertEquals(InvalidPositionCoordinate.getErrorMessage(position.getXLabel(), position.getYLabel()), thrown.getMessage());
    }

//    Test for AC6.6
    @Test
    public void movePieceNotOwnPieceTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        player2 = PlayerFactory.createHuman(Color.BLACK, game);
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
