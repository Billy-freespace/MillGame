package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.exceptions.RankedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

public class GameEventActionTest {

    private MillGame game;
    private Board board;
    private EventAction eventAction;
    private ActionEvent event;

    @BeforeEach
    public void buildGame() throws RankedException {
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS; //default game variant
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;

        game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();
        board = game.getBoard();
    }

    @Test
    public void nextTurnTest(){
        Position position = board.getOrigin();
        Player opponent = game.getOpponentPlayer();

        event = new ActionEvent(position, -1, "nextTurnTest unit test: " + position);

        eventAction = game.getEventAction();
        eventAction.actionPerformed(event); // place a piece in origin position

        assertEquals(opponent, game.getActivePlayer());
    }
/*
    @Test
    public void
 */
}
