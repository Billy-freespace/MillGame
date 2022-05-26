package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TurnIteratorTest {
    private MillGame game;

    @BeforeEach
    public void initTurnIterator() throws RankedException {
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS; //default game variant
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;

        game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();
    }

    @Test
    public void nextTurnTest()
    {
        Player player = game.getActivePlayer();
        Player opponent = game.getOpponentPlayer();

        assertEquals(opponent, game.nextTurn());
        assertEquals(opponent, game.getActivePlayer());
        assertEquals(player, game.nextTurn());
    }
}
