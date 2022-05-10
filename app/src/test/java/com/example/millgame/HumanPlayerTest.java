package com.example.millgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.HumanPlayer;

public class HumanPlayerTest {

    private MillGame game = new MillGameBuilder().build(MillGame.GameVariant.NINE_MEN_MORRIS, MillGame.GameMode.HUMAN_HUMAN);
    private HumanPlayer player1 = new HumanPlayer(PieceColor.WHITE, game);
 
    @Test
    public void InvalidNoPieces () {
        player1.npieces = 9;
        assertThrows(NoPiecesError.class, () -> {
            player1.placePiece('d', 1);;
        });
    }
    
    @Test
     public void placePieceTest() {
         ;
     }
}