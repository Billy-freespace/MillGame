package com.example.millgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.InvalidPositionCoordinate;//
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.HumanPlayer;
import com.example.millgame.players.HumanPlayerFactory;

public class HumanPlayerTest {

    private MillGame game = new MillGameBuilder().build(MillGame.GameVariant.NINE_MEN_MORRIS, MillGame.GameMode.HUMAN_HUMAN);
    private Player player2 = new HumanPlayer(PieceColor.WHITE, game);

    //private final MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
    //private  Board board;////
    //private Player player2;////

    /*@BeforeEach
    public void createNineMMBoard(){
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(GameVariant.NINE_MEN_MORRIS);
        player2 = new HumanPlayerFactory().create(PieceColor.BLACK, board);
        System.out.println(player2);
    }*/
    
 
    @Test
    public void TmpInvalidNoPiecesTest () throws InvalidPositionCoordinate, NoPiecesError {
        int j = 0;
        for (char i = 'a'; i <= 'g';  i++) {
            j++;
            if (i == 'd') continue;
            player2.placePiece(i, j);
        }

        player2.placePiece('a', 7);
        player2.placePiece('b', 6);
        player2.placePiece('c', 5);
        

        assertThrows(NoPiecesError.class, () -> {
            player2.placePiece('e', 3);;
        });
    }
    
    @Test
     public void placePieceTest() {
         ;
     }
}