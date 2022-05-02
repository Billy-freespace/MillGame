package com.example.millgame;

//import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TurnIteratorTest {

    private Player player0;
    private Player player1;
    private ArrayList<Player> players;

    @BeforeAll
    public void createPlayers(){
        Board board = BoardCreatorDirector.makeMMBoard(GameVariant.NINE_MEN_MORRIS);
        player0 = new HumanPlayer(PieceColor.WHITE, board);
        player1 = new HumanPlayer(PieceColor.BLACK, board);

        players = new ArrayList<Player>();
        players.add(player0);
        players.add(player1);
    }

    @Test
    public void testNext()
    {
        TurnIterator itr = new TurnIterator(players);

        assertEquals(player0, itr.next());
        assertEquals(player1, itr.next());
        assertEquals(player0, itr.next());
    }

    @Test
    public void testGetOpponent() throws CloneNotSupportedException {
        TurnIterator itr = new TurnIterator(players);

        Player player = itr.next(); // player0
        Player opponent = itr.getOpponent(); //player1

        assertEquals(player0, itr.getIterationState());
        assertEquals(player1, opponent);
    }
}
