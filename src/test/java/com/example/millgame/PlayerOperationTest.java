package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.actions.RemovingEventAction;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.*;

import com.example.millgame.misc.BoardCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@Disabled("PlayerFactory.create(PlayerType, Color, Board) method was delete - UPDATE")
class PlayerOperationTest {

    private MillGame game;
    private NineMMBoard board;
    private Player player;
    private Player opponent;
    private EventAction eventAction;
    private ActionEvent event;

    //@Disabled
    @BeforeEach
    public void createPlayers() throws RankedException {
        //int boardPieces = 0;
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

    public void placeAllPieces() throws NotEmptyPosition, InvalidPositionCoordinate {
        int j = 1;
        try {
            // 3 fichas
            for (char i = 'a'; i <= 'g';  i++, j++) {
                if (i == 'd') continue;
                player.placePiece(i, j);
            }

            player.placePiece('a', 7);
            player.placePiece('d', 1);
            player.placePiece('c', 5);
        } catch (NoPiecesError e){
            // DONOTHING
        }
    }

    private void placePieces(List<BoardCoordinate> coordinates) throws InvalidPositionCoordinate {
        Board board = game.getBoard();
        for(BoardCoordinate coordinate : coordinates){
            Position position = board.getPosition(coordinate.getX(), coordinate.getY());

            ActionEvent event = new ActionEvent(position, -1, "TESTING");
            EventAction eventAction = game.getEventAction(); // PositioningEventAction
            eventAction.actionPerformed(event);
        }
    }

    //PLACE TESTS
    //@Disabled
    @Test
    public void placePieceTest() throws NotEmptyPosition, InvalidPositionCoordinate{
        /*
         * Test for AC3.1
         */
        int boardPieces = 0;
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
        boardPieces = player.countBoardPieces();
        assertEquals(1, boardPieces);
    }

    //@Disabled
    @Test
    public void placePieceOnOccupiedPositionTest() throws InvalidPositionCoordinate{
        /*
         * Test for AC3.2
         */
        int boardPieces = 0;
        try {
            player.placePiece('a', 7);
            player.placePiece('b', 6);
        } catch (NoPiecesError | NotEmptyPosition e){
            // DONOTHING
        }
         boardPieces = player.countBoardPieces();
        assertEquals(2, boardPieces);
        assertThrows(NotEmptyPosition.class, () -> {
            player.placePiece('a', 7);
        });
    }

    //@Disabled
    @Test
    public void placePieceOnInvalidPositionTest() throws NotEmptyPosition, InvalidPositionCoordinate{
        /*
         * Test for AC3.3
         */
        int boardPieces = 0;
        char xLabel = 'z';
        int yLabel = 100;

        assertThrows(InvalidPositionCoordinate.class, () -> {
            player.placePiece(xLabel, yLabel);
        });
        boardPieces = player.countBoardPieces();
        assertEquals(0, boardPieces);

    }

    //@Disabled
    @Test
    public void noPositioningPiecesTest() throws NotEmptyPosition, InvalidPositionCoordinate{
        /*
         * Test for AC3.4
         */

        placeAllPieces();

        assertThrows(NoPiecesError.class, () -> {
            player.placePiece('e', 3);;
        });
    }

//    Test for AC5.1
    @Test
    public void removePieceTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        opponent = game.getOpponentPlayer();

        /*
         * STATE: 2 white pieces was already placed in b2 and b6 positions
         * Placing a white piece in position b4, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   | W |   |---|   |---|   |
         * 5|   |   | B |---| B |   |   |
         * 4|---| * |---|   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   | W |   |---|   |---|   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('b', 2)); // first white piece placed
        coordinates.add(new BoardCoordinate('c', 5)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 6)); // second white piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // second black piece placed
        coordinates.add(new BoardCoordinate('b', 4)); // third white piece placed
        placePieces(coordinates);

        Position position = board.getPosition('c', 5);
        event = new ActionEvent(position, -1, "removePieceTest unit test: " + position);
        eventAction = game.getEventAction();
        eventAction.actionPerformed(event); // Remove piece of the opponent player in the origin position

        assertNull(position.getPiece());
        assertEquals(opponent, game.getActivePlayer());
    }

//    Test for AC5.2
    @Disabled("Update TEST - STATE: FAILING")
    @Test
    public void removePieceInMillTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        /*
         * W : TESTING (default color: WHITE, BLACK)
         * CASO: B: a formado un molino [c5, d5, e5]
         *       W: forma un molino [b2, b4, b6] -> REMOVER PIECE -> c5 (ERRROR: pieza en molino)
         * MOVIMIETOS: (W: a1 - B: c5 - W: b6 - B: d5 - W: b4 - B: e5 (DELETE: a1) - W: b2 (DELETE: c5 - NO EVENT: MANUAL)
         *
         * NOTE: W#, B#: Pieza a eliminar
         * 7|---|   |   |---|   |   |---|
         * 6|   | W |   |---|   |---|   |
         * 5|   |   | B#| B | B |   |   |
         * 4|---| W |---|   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   | * |   |---|   |---|   |
         * 1| W#|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        opponent = game.getOpponentPlayer();
        Position origin = board.getOrigin();
        Position a4 = board.getPosition('a', 4);
        Position a7 = board.getPosition('a', 7);
        Position g1 = board.getPosition('g', 1);
        opponent.placePiece(origin);
        opponent.placePiece(a4);
        opponent.placePiece(a7);
        opponent.placePiece(g1);
        game.changeEventAction(new RemovingEventAction());

        event = new ActionEvent(origin, -1, "removePieceTest unit test: " + origin);
        eventAction = game.getEventAction();

        RemovePieceFromMillError thrown = assertThrows(RemovePieceFromMillError.class,
                () -> eventAction.actionPerformed(event)
        );
        assertEquals(RemovePieceFromMillError.getMessageError(origin.getPiece()), thrown.getMessage());
    }

//    Test for AC6.1
    //@Disabled
    @Test
    public void movePieceTest() throws RankedException {
        // place a4 (pieza) -> get pieza a4 -> mueve pieza al origin -> pieza == origin.getPiece()
        //Position origin = board.getOrigin();
        Position endPosition = board.getPosition('g', 1);
        Position startPosition = board.getPosition('d', 1);
        //player.placePiece(position);
        placeAllPieces();
        Piece piece = startPosition.getPiece();


        player.movePiece(piece, endPosition);


        assertEquals(piece, endPosition.getPiece());
        assertNull(startPosition.getPiece());
    }

/*
//    Test for AC6.3
    @Test
    public void movePieceNotEmptyPositionTest() throws InvalidPositionCoordinate, NotEmptyPosition, NoPiecesError {
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

 */

    @Test
    public void movePieceNotNeighbourTest() throws RankedException {
        //Position origin = board.getOrigin();
        Position startPosition = board.getOrigin();
        Position endPosition = board.getPosition('g', 1);
        placeAllPieces();
        Piece piece = startPosition.getPiece();
        assertThrows(InvalidMovement.class, () -> {
            player.movePiece(piece, endPosition);
        });
        assertNotNull(startPosition.getPiece());
        assertEquals(piece, startPosition.getPiece());
        assertNull(endPosition.getPiece());
    }

    @Test
    public void movePieceNoEmptyPositionTest() throws InvalidPositionCoordinate, NotEmptyPosition, NoPiecesError {
        //Position origin = board.getOrigin();
        Position startPosition = board.getOrigin();
        Position endPosition = board.getPosition('d', 1);
        placeAllPieces();
        Piece piece = startPosition.getPiece();

        NotEmptyPosition thrown = assertThrows(NotEmptyPosition.class,
                () -> player.movePiece(piece, endPosition));
        /*assertEquals(NotEmptyPosition.getErrorMessage(origin.getXLabel(), origin.getYLabel()), thrown.getMessage());*/
    }

//    Test for AC6.5
    //@Disabled
    @Test
    public void movePieceInvalidPositionTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {

        Position origin = board.getOrigin();
        Position position = new Position('a', -1);
        //player.placePiece(origin);
        placeAllPieces();
        Piece piece = origin.getPiece();

        InvalidPositionCoordinate thrown = assertThrows(InvalidPositionCoordinate.class,
                () -> player.movePiece(piece, position)
        );

        assertEquals(InvalidPositionCoordinate.getErrorMessage(position.getXLabel(), position.getYLabel()), thrown.getMessage());
    }

//    Test for AC6.6
    //@Disabled
    @Test
    public void movePieceNotOwnPieceTest() throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        Position origin = board.getOrigin();
        player.placePiece(origin);
        game.nextTurn();
        opponent = game.getActivePlayer();

        NotOwnPiece thrown = assertThrows(NotOwnPiece.class,
                () -> opponent.movePiece(origin.getPiece(), 'a', 4)
        );
        assertEquals(NotOwnPiece.getErrorMessage(origin.getPiece()), thrown.getMessage());
    }

    //@Disabled
    @Test
    public void flyPieceTest() throws RankedException {
        Position endPosition = board.getOrigin();
        Position startPosition = board.getPosition('e', 5);
         int j = 2;
        try {
            // 3 fichas
            for (char i = 'b'; i <= 'e';  i++, j++) {
                if (i == 'd') continue;
                player.placePiece(i, j);
            }
        } catch (NoPiecesError e){
            // DONOTHING
        }
        Piece piece = startPosition.getPiece();
        player.movePiece(piece, endPosition);
        assertEquals(piece, endPosition.getPiece());
        assertNull(startPosition.getPiece());
    }

    //@Disabled
    @Test
    public void flyPieceInvalidPositionTest() throws RankedException {
        Position origin = board.getOrigin();
        Position position = new Position('a', -1);
         int j = 1;
        try {
            // 3 fichas
            for (char i = 'a'; i <= 'e';  i+=2, j+=2) {
                if (i == 'd') continue;
                player.placePiece(i, j);
            }
        } catch (NoPiecesError e){
            // DONOTHING
        }
        Piece piece = origin.getPiece();
        InvalidPositionCoordinate thrown = assertThrows(InvalidPositionCoordinate.class,
                () -> player.movePiece(piece, position)
        );

        assertEquals(InvalidPositionCoordinate.getErrorMessage(position.getXLabel(), position.getYLabel()), thrown.getMessage());
    }

    //@Disabled
    @Test
    public void flyPieceNoEmptyTest() throws RankedException {
        Position endPosition = board.getOrigin();
        Position startPosition = board.getPosition('e', 5);
         int j = 1;
        try {
            // 3 fichas
            for (char i = 'a'; i <= 'e';  i+=2, j+=2) {
                if (i == 'd') continue;
                player.placePiece(i, j);
            }
        } catch (NoPiecesError e){
            // DONOTHING
        }
        Piece piece = startPosition.getPiece();
        NotEmptyPosition thrown = assertThrows(NotEmptyPosition.class,
                () -> player.movePiece(piece, endPosition));
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
