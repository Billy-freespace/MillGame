    package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.exceptions.RankedException;
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
    private Player player1, player2;

    @BeforeEach
    public void createNineMMBoard() {
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(variant);
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
        @BeforeEach
        public void createPlayers() {
            player1 = PlayerFactory.create(PlayerType.HUMAN, PieceColor.WHITE, board);
            player2 = PlayerFactory.create(PlayerType.HUMAN, PieceColor.WHITE, board);
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
            } catch (NoPiecesError | InvalidPositionCoordinate e){
                // DONOTHING
            }

            assertThrows(NoPiecesError.class, () -> {
                player1.placePiece('e', 3);;
            });

        }

        @Test
        public void getInvalidPositionTest() {
            char xLabel = 'c';
            int yLabel = 1;

            assertThrows(InvalidPositionCoordinate.class, () -> {
                player2.placePiece(xLabel, yLabel);
            });
        }

        @Test
        public void getNoEmptyPositionTest() throws RankedException  {
            try {
                player2.placePiece('a', 7);
                player2.placePiece('b', 6);
            } catch (NoPiecesError | InvalidPositionCoordinate e){
                // DONOTHING
            }

            assertThrows(RankedException.class, () -> {
                player2.placePiece('a', 7);
            });
        }
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
    public void removePieceTest() throws RankedException{
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