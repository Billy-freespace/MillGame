package com.example.millgame;

import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.misc.Color;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


@Disabled("PlayerFactory.create(PlayerType, Color, Board) method was delete - UPDATE")
public class TurnIteratorTest {
    private Player player0;
    private Player player1;
    private ArrayList<Player> players;

    private TurnIterator itr;

    @BeforeEach
    public void initTurnIterator(){
        Board board = BoardCreatorDirector.makeMMBoard(MillGame.GameVariant.NINE_MEN_MORRIS);
        player0 = PlayerFactory.create(PlayerType.HUMAN, Color.WHITE, board);
        player1 = PlayerFactory.create(PlayerType.HUMAN, Color.BLACK, board);

        players = new ArrayList<Player>(2);
        players.add(player0);
        players.add(player1);
        itr = new TurnIterator(players);
    }

    @Test
    public void nextTurnTest()
    {
        assertEquals(player0, itr.next());
        assertEquals(player1, itr.next());
        assertEquals(player0, itr.next());
    }

    @Test
    public void playerOpponentTest() throws CloneNotSupportedException {
        Player player0 = itr.next();
        Player opponent = itr.getOpponent(); // getOpponent does not move the iterator

        assertEquals(player1, opponent);
        assertEquals(player1, itr.next());
    }

    @Test
    public void getIterationStateTest(){
        Player player0 = itr.next();

        assertEquals(player0, itr.getIterationState());
    }
}
