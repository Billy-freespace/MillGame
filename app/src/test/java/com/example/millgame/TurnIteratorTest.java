package com.example.millgame;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.boards.NineMMBoard;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.HumanPlayer;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class TurnIteratorTest {
    private Player player0;
    private Player player1;
    private ArrayList<Player> players;
    private NineMMBoard board; // default board

    @BeforeAll
    public void createPlayers(){
        board = (NineMMBoard) BoardCreatorDirector.makeMMBoard(GameVariant.NINE_MEN_MORRIS);
        player0 = new HumanPlayer(PieceColor.WHITE, board);
        player1 = new HumanPlayer(PieceColor.BLACK, board);

        players = new ArrayList<Player>();
        players.add(player0);
        players.add(player1);
    }

    @Test
    public void nextTurnTest()
    {
        TurnIterator itr = new TurnIterator(players);

        assertEquals(player0, itr.next());
        assertEquals(player1, itr.next());
        assertEquals(player0, itr.next());
    }

    @Test
    public void playerOpponentTest() throws CloneNotSupportedException {
        TurnIterator itr = new TurnIterator(players);

        Player player = itr.next(); // player0
        Player opponent = itr.getOpponent(); //player1

        assertEquals(player0, itr.getIterationState());
        assertEquals(player1, opponent);
    }
}
