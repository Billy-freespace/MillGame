package com.example.millgame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.millgame.Board;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.HumanPlayer;
import com.example.millgame.players.HumanPlayerFactory;


import org.junit.jupiter.api.Test;

public class HumanPlayerFactoryTest {
    @Test
    public void createTest () {
        Board board = new NineMMBoard();
        Player esperado = new HumanPlayer(PieceColor.WHITE, board);
        Player real = (new HumanPlayerFactory()).create(PieceColor.WHITE, board);
        //assertEquals(esperado, real);
    }
}
