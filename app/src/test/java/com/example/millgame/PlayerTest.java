package com.example.millgame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.HumanPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public  class PlayerTest {

    private MillGame  game = new MillGame();
    private HumanPlayer player1 = new HumanPlayer(PieceColor.WHITE, game);
 
    

    public void InvalidNoPieces () {
        player1.npieces = 9;
        assertThrows(NoPiecesError.class, () -> {
            player1.placePiece('d', 1);;
        });
    }
}
