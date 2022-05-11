package com.example.millgame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.millgame.Board;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.HumanPlayer;
import com.example.millgame.players.HumanPlayerFactory;


import org.junit.jupiter.api.Test;

public class HumanPlayerFactoryTest {
    /*
     * PlayerFactory was changed - refactor this test
    @Test
    public void createTest () { // NOTE: Change board by game (ASAP)
        //Board board = new NineMMBoard();
        MillGame game = null; // initialize MillGame with MillGameBuilder
        Player esperado = new HumanPlayer(PieceColor.WHITE, game);
        Player real = (new HumanPlayerFactory()).create(PieceColor.WHITE, game);
        //assertEquals(esperado, real);
    }
     */
}
