package com.example.millgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.millgame.exceptions.InvalidPositionCoordinate;//
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.HumanPlayer;

public class HumanPlayerTest {

    private MillGame game = new MillGameBuilder().build(MillGame.GameVariant.NINE_MEN_MORRIS, MillGame.GameMode.HUMAN_HUMAN);
    private HumanPlayer player1 = new HumanPlayer(PieceColor.WHITE, game);
 
    @Test
    public void InvalidNoPiecesTest () throws InvalidPositionCoordinate, NoPiecesError {
        int j = 0;
        for (char i = 'a'; i <= 'g';  i++) {
            j++;
            if (i == 'd') continue;
            player1.placePiece(i, j);
        }

        player1.placePiece('a', 7);
        player1.placePiece('b', 6);
        player1.placePiece('c', 5);
        

        assertThrows(NoPiecesError.class, () -> {
            player1.placePiece('e', 3);;
        });
    }

    
    @Test
     public void placePieceTest() {
         ;
     }
}