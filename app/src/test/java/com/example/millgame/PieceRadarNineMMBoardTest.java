package com.example.millgame;

import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.BoardCoordinate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PieceRadarNineMMBoardTest {
    private final MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
    private final MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
    private MillGame game;

    @BeforeAll
    public void initGame() throws RankedException {
        game = new MillGameBuilder().build(variant, mode);
    }

    @Test
    public void verticalMillCentralPieceTest() throws NoPiecesError, InvalidPositionCoordinate, NotEmptyPosition {
        /*
         * State: 2 pieces was already placed in b2 and b4 positions
         * Placing a piece in position b3, we form a mill
         */

        BoardCoordinate startCoordinate = new BoardCoordinate('b', 6);
        List<BoardCoordinate> coordinates = new ArrayList<BoardCoordinate>();
        coordinates.add(new BoardCoordinate('b', 2)); // first write piece placed
        coordinates.add(new BoardCoordinate('a', 1)); // first black piece placed
        coordinates.add(new BoardCoordinate('b', 4)); // second write piece placed
        coordinates.add(new BoardCoordinate('a', 3)); // second write piece placed
        coordinates.add(startCoordinate); // third write piece placed

        // a mill formed by white player (b2, b4, b6)

        Player player;
        for(BoardCoordinate coordinate : coordinates){
            player = game.getActivePlayer();
            player.placePiece(coordinate.getX(), coordinate.getY());
            game.nextTurn();
        }
/*
        Piece piece = game.get
        game.getMills()
*/


    }

    @Test
    public void verticalMillLowerPieceTest(){

    }

    @Test
    public void verticalMillUpperPieceTest(){

    }

    @Test
    public void horizontalMillCentralPieceTest(){

    }

    @Test
    public void horizontalMillLowerPieceTest(){

    }

    @Test
    public void horizontalMillUpperPieceTest(){

    }

    @Test
    public void notFoundMillOnePiecesTest(){

    }

    @Test
    public void notFoundMillTwoPiecesTest(){

    }

    @Test
    public void multiMillTest(){

    }
}
