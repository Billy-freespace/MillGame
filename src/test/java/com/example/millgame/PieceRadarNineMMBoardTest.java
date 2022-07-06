package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.BoardCoordinate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

//@Disabled("MillGameBuilder was changed - UPDATE test case")
public class PieceRadarNineMMBoardTest {
    private MillGame game;

    /*
     * NINE_MEN_MORRIS board (EMPTY BOARD)
     *  ---: empty position
     *
     * 7|---|   |   |---|   |   |---|
     * 6|   |---|   |---|   |---|   |
     * 5|   |   |---|---|---|   |   |
     * 4|---|---|---|   |---|---|---|
     * 3|   |   |---|---|---|   |   |
     * 2|   |---|   |---|   |---|   |
     * 1|---|   |   |---|   |   |---|
     *    a   b   c   d   e   f   g
     */

    @BeforeEach
    public void initGame() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;

        game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();
    }

    private void placePieces(List<BoardCoordinate> coordinates) throws RankedException{
        Board board = game.getBoard();
        for(BoardCoordinate coordinate : coordinates){
            Position position = board.getPosition(coordinate.getX(), coordinate.getY());

            ActionEvent event = new ActionEvent(position, -1, "TESTING");
            EventAction eventAction = game.getEventAction(); // PositioningEventAction
            eventAction.actionPerformed(event);
        }
    }

    @Test
    public void verticalMillCentralPieceTest() throws NoPiecesError, InvalidPositionCoordinate, NotEmptyPosition, RankedException {
        /*
         * STATE: 2 white pieces was already placed in b2 and b6 positions
         * Placing a white piece in position b4, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   | W |   |---|   |---|   |
         * 5|   |   | B |---| B |   |   |
         * 4|---| * | W |   |---|---|---|
         * 3|   |   | B |---|---|   |   |
         * 2|   | W |   |---|   |---|   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('b', 2)); // first write piece placed
        coordinates.add(new BoardCoordinate('c', 3)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 6)); // second write piece placed
        coordinates.add(new BoardCoordinate('c', 5)); // second black piece placed
        coordinates.add(new BoardCoordinate('c', 4)); // third write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // third black piece placed
        BoardCoordinate millCoordinate = new BoardCoordinate('b', 4);
        coordinates.add(millCoordinate); // four write piece placed

        // a mill formed by white player (b2, b4, b6)

        placePieces(coordinates);

        char x = millCoordinate.getX();
        int y = millCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece b2 = game.getPiece('b', 2);
        Piece b6 = game.getPiece('b', 6);
        Piece b4 = millPiece;

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(b2)),
                () -> assertTrue(mill.hasPiece(b4)),
                () -> assertTrue(mill.hasPiece(b6)));
    }

    @Test
    public void verticalMillLowerPieceTest() throws NoPiecesError, InvalidPositionCoordinate, NotEmptyPosition, RankedException {
        /*
         * STATE: 2 white pieces was already placed in b2 and b6 positions
         * Placing a white piece in position b2, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill
         *
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   | W |   |---|   |---|   |
         * 5|   |   | B |---| B |   |   |
         * 4|---| W | W |   |---|---|---|
         * 3|   |   | B |---|---|   |   |
         * 2|   | * |   |---|   |---|   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         *
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('b', 4)); // first write piece placed
        coordinates.add(new BoardCoordinate('c', 3)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 6)); // second write piece placed
        coordinates.add(new BoardCoordinate('c', 5)); // second black piece placed
        coordinates.add(new BoardCoordinate('c', 4)); // third write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // third black piece placed
        BoardCoordinate millCoordinate = new BoardCoordinate('b', 2);
        coordinates.add(millCoordinate); // four write piece placed

        // a mill formed by white player (b2, b4, b6)

        placePieces(coordinates);

        char x = millCoordinate.getX();
        int y = millCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece b6 = game.getPiece('b', 6);
        Piece b4 = game.getPiece('b', 4);
        Piece b2 = millPiece;

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(b2)),
                () -> assertTrue(mill.hasPiece(b4)),
                () -> assertTrue(mill.hasPiece(b6)));
    }

    @Test
    public void verticalMillUpperPieceTest() throws NoPiecesError, InvalidPositionCoordinate, NotEmptyPosition, RankedException{
        /*
         * STATE: 2 white pieces was already placed in b2 and b6 positions
         * Placing a white piece in position b6, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill (called: millCoordinate)
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   | * |   |---|   |---|   |
         * 5|   |   | B |---| B |   |   |
         * 4|---| W | W |   |---|---|---|
         * 3|   |   | B |---|---|   |   |
         * 2|   | W |   |---|   |---|   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('b', 4)); // first write piece placed
        coordinates.add(new BoardCoordinate('c', 3)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 2)); // second write piece placed
        coordinates.add(new BoardCoordinate('c', 5)); // second black piece placed
        coordinates.add(new BoardCoordinate('c', 4)); // third write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // third black piece placed
        BoardCoordinate millCoordinate = new BoardCoordinate('b', 6); // coordinate of piece to form a mill
        coordinates.add(millCoordinate); // four write piece placed

        // a mill formed by white player (b2, b4, b6)

        placePieces(coordinates);

        char x = millCoordinate.getX();
        int y = millCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece b2 = game.getPiece('b', 2);
        Piece b4 = game.getPiece('b', 4);
        Piece b6 = millPiece;

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(b2)),
                () -> assertTrue(mill.hasPiece(b4)),
                () -> assertTrue(mill.hasPiece(b6)));
    }

    @Test
    public void horizontalMillCentralPieceTest() throws RankedException{
        /*
         * STATE: 2 white pieces was already placed in b2 and f2 positions
         * Placing a white piece in position d2, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill (called: millCoordinate)
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---| B |   |   |
         * 4|---|---|---|   |---|---|---|
         * 3|   |   | B | W | B |   |   |
         * 2|   | W |   | * |   | W |   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('b', 2)); // first write piece placed
        coordinates.add(new BoardCoordinate('c', 3)); // first black piece placed
        coordinates.add(new BoardCoordinate('f', 2)); // second write piece placed
        coordinates.add(new BoardCoordinate('e', 3)); // second black piece placed
        coordinates.add(new BoardCoordinate('d', 3)); // third write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // third black piece placed
        BoardCoordinate centralCoordinate = new BoardCoordinate('d', 2); // coordinate of piece to form a mill
        coordinates.add(centralCoordinate); // four write piece placed

        // a mill formed by white player (b2, d2, f2)

        placePieces(coordinates);

        char x = centralCoordinate.getX();
        int y = centralCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece b2 = game.getPiece('b', 2);
        Piece f2 = game.getPiece('f', 2);
        Piece d2 = millPiece;

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(b2)),
                () -> assertTrue(mill.hasPiece(d2)),
                () -> assertTrue(mill.hasPiece(f2)));
    }

    @Test
    public void horizontalMillLowerPieceTest() throws RankedException {
        /*
         * STATE: 2 white pieces was already placed in d2 and f2 positions
         * Placing a white piece in position b2, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill (called: millCoordinate)
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---| B |   |   |
         * 4|---|---|---|   |---|---|---|
         * 3|   |   | B | W | B |   |   |
         * 2|   | * |   | W |   | W |   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         *
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('d', 2)); // first write piece placed
        coordinates.add(new BoardCoordinate('c', 3)); // first black piece placed
        coordinates.add(new BoardCoordinate('f', 2)); // second write piece placed
        coordinates.add(new BoardCoordinate('e', 3)); // second black piece placed
        coordinates.add(new BoardCoordinate('d', 3)); // third write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // third black piece placed
        BoardCoordinate centralCoordinate = new BoardCoordinate('b', 2); // coordinate of piece to form a mill
        coordinates.add(centralCoordinate); // four write piece placed

        // a mill formed by white player (b2, d2, f2)

        placePieces(coordinates);

        char x = centralCoordinate.getX();
        int y = centralCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece b2 = millPiece;
        Piece d2 = game.getPiece('d', 2);
        Piece f2 = game.getPiece('f', 2);

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(b2)),
                () -> assertTrue(mill.hasPiece(d2)),
                () -> assertTrue(mill.hasPiece(f2)));
    }

    @Test
    public void horizontalMillUpperPieceTest() throws RankedException{
        /*
         * STATE: 2 white pieces was already placed in d2 and b2 positions
         * Placing a white piece in position f2, we will form a mill
         * W: White, B: Black, *: position to be placed the white piece to form a mill (called: millCoordinate)
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---| B |   |   |
         * 4|---|---|---|   |---|---|---|
         * 3|   |   | B | W | B |   |   |
         * 2|   | W |   | W |   | * |   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         *
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('d', 2)); // first write piece placed
        coordinates.add(new BoardCoordinate('c', 3)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 2)); // second write piece placed
        coordinates.add(new BoardCoordinate('e', 3)); // second black piece placed
        coordinates.add(new BoardCoordinate('d', 3)); // third write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // third black piece placed
        BoardCoordinate centralCoordinate = new BoardCoordinate('f', 2); // coordinate of piece to form a mill
        coordinates.add(centralCoordinate); // four write piece placed

        // a mill formed by white player (b2, d2, f2)

        placePieces(coordinates);

        char x = centralCoordinate.getX();
        int y = centralCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece b2 = game.getPiece('b', 2);
        Piece d2 = game.getPiece('d', 2);
        Piece f2 = millPiece;

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(b2)),
                () -> assertTrue(mill.hasPiece(d2)),
                () -> assertTrue(mill.hasPiece(f2)));
    }

    @Test
    public void horizontalMillUpperRow4Test(){
        /*
         * NINE_MEN_MORRIS board (EMPTY BOARD)
         *  ---: empty position
         *
         * 7| B |   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---|---|   |   |
         * 4| W | W | * |   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   | B |   |---|   |---|   |
         * 1|---|   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('a', 4)); // first write piece placed
        coordinates.add(new BoardCoordinate('a', 7)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 4)); // second write piece placed
        coordinates.add(new BoardCoordinate('b', 2)); // second black piece placed
        BoardCoordinate finalPiece = new BoardCoordinate('c', 4); // coordinate of piece to form a mill
        coordinates.add(finalPiece); // four write piece placed

        // a mill formed by white player (a4, b4, c4)

        placePieces(coordinates);

        char x = finalPiece.getX();
        int y = finalPiece.getY();

        Piece millPiece = game.getPiece(x, y);
        Piece a4 = game.getPiece('a', 4);
        Piece b4 = game.getPiece('b', 4);
        Piece c4 = millPiece;

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(1, mills.size());

        Board.Mill mill = mills.get(0);
        assertAll(
                () -> assertTrue(mill.hasPiece(a4)),
                () -> assertTrue(mill.hasPiece(b4)),
                () -> assertTrue(mill.hasPiece(c4)));
    }

    @Test
    public void notFoundMillOnePiecesTest() throws RankedException{
        /*
         * STATE: 1 white piece was placed in a1, so no mill was formed
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---|---|   |   |
         * 4|---|---|---|   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   |---|   |---|   |---|   |
         * 1| W |   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        char x = 'a';
        int y = 1;

        Player player = game.getActivePlayer();
        player.placePiece(x, y);
        Piece piece = game.getPiece(x, y);

        List<Board.Mill> mills = game.getMills(piece);
        assertEquals(0, mills.size());
    }

    @Test
    public void notFoundVerticalMillTwoPiecesTest() throws RankedException {
        /*
         * STATE: 2 white pieces was placed in a1 and a4, but no mill was formed
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---|---|   |   |
         * 4| W |---|---|   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   | B |   |---|   |---|   |
         * 1| W |   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */


        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('a', 1)); // first write piece placed
        coordinates.add(new BoardCoordinate('b', 2)); // first black piece placed
        BoardCoordinate lastWhiteCoordinate = new BoardCoordinate('a', 4);
        coordinates.add(lastWhiteCoordinate);

        placePieces(coordinates);

        char x = lastWhiteCoordinate.getX();
        int y = lastWhiteCoordinate.getY();

        Piece piece = game.getPiece(x, y);

        List<Board.Mill> mills = game.getMills(piece);
        assertEquals(0, mills.size());
    }


    @Test
    public void notFoundHorizontalMillTwoPiecesTest() throws RankedException {
        /*
         * STATE: 2 white pieces was placed in a1 and a4, but no mill was formed
         *
         * 7|---|   |   |---|   |   |---|
         * 6|   |---|   |---|   |---|   |
         * 5|   |   |---|---|---|   |   |
         * 4|---|---|---|   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   | B |   |---|   |---|   |
         * 1| W |   |   | W |   |   |---|
         *    a   b   c   d   e   f   g
         */


        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('a', 1)); // first write piece placed
        coordinates.add(new BoardCoordinate('b', 2)); // first black piece placed
        BoardCoordinate lastWhiteCoordinate = new BoardCoordinate('d', 1);
        coordinates.add(lastWhiteCoordinate);

        placePieces(coordinates);

        char x = lastWhiteCoordinate.getX();
        int y = lastWhiteCoordinate.getY();

        Piece piece = game.getPiece(x, y);

        List<Board.Mill> mills = game.getMills(piece);
        assertEquals(0, mills.size());
    }

    @Test
    public void multiMillTest() throws RankedException{
        /*
         * STATE: 4 white pieces was placed in a1, a4, b4 and c4, but no mill was formed
         * Placing a white piece in position a2, we will form two mills
         *
         * 7| W |   |   |---|   |   |---|
         * 6|   | B |   |---|   |---|   |
         * 5|   |   | B |---| B |   |   |
         * 4| * | W | W |   |---|---|---|
         * 3|   |   |---|---|---|   |   |
         * 2|   | B |   |---|   |---|   |
         * 1| W |   |   |---|   |   |---|
         *    a   b   c   d   e   f   g
         */

        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('a', 1)); // first write piece placed
        coordinates.add(new BoardCoordinate('b', 2)); // first black piece placed
        coordinates.add(new BoardCoordinate('a', 7)); // second write piece placed
        coordinates.add(new BoardCoordinate('b', 6)); // second black piece placed
        coordinates.add(new BoardCoordinate('b', 4)); // third write piece placed
        coordinates.add(new BoardCoordinate('c', 5)); // third black piece placed
        coordinates.add(new BoardCoordinate('c', 4)); // fourth write piece placed
        coordinates.add(new BoardCoordinate('e', 5)); // fourth black piece placed
        BoardCoordinate millCoordinate = new BoardCoordinate('a', 4);
        coordinates.add(millCoordinate);

        placePieces(coordinates);

        char x = millCoordinate.getX();
        int y = millCoordinate.getY();

        Piece millPiece = game.getPiece(x, y);

        List<Board.Mill> mills = game.getMills(millPiece);
        assertEquals(2, mills.size());

        Piece a1 = game.getPiece('a', 1);
        Piece a7 = game.getPiece('a', 7);
        Piece b4 = game.getPiece('b', 4);
        Piece c4 = game.getPiece('c', 4);
        Piece a4 = millPiece;

        Board.Mill firstMill = mills.get(0);
        Board.Mill secondMill = mills.get(1);

        assertAll(
                () -> assertTrue(firstMill.hasPiece(a1)),
                () -> assertTrue(firstMill.hasPiece(a4)),
                () -> assertTrue(firstMill.hasPiece(a7)),
                () -> assertTrue(secondMill.hasPiece(a4)),
                () -> assertTrue(secondMill.hasPiece(b4)),
                () -> assertTrue(secondMill.hasPiece(c4))
        );
    }
}
