package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("No X11 configuration - Github Action server")
public class GameGUIMinimalTest {
    @Test
    public void emptyNineMMBoardTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;

        MillGame game = new MillGameBuilder(variant)
                    .reset()
                    .buildBoard()
                    .setRandomTurn(false)
                    .initTurnIterator()
                    .createPlayers(mode)
                    .build();

        GameGUIMinimal gameGUI = new GameGUIMinimal(game);

        try {
            gameGUI.setVisible(true);
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initializedNineMMBoardTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        MillGame game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();

        // initialization of game
        Player player = game.getActivePlayer();
        player.placePiece('a', 1);

        player = game.nextTurn();
        player.placePiece('b', 2);

        GameGUIMinimal gameGUI = new GameGUIMinimal(game);

        try {
            gameGUI.setVisible(true);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void twelveMMBoardPanelTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;
        MillGame game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();

        GameGUIMinimal gameGUI = new GameGUIMinimal(game);

        try {
            gameGUI.setVisible(true);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sixMMBoardPanelTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.SIX_MEN_MORRIS;
        MillGame game = new MillGameBuilder(variant)
                .reset()
                .buildBoard()
                .setRandomTurn(false)
                .initTurnIterator()
                .createPlayers(mode)
                .build();

        GameGUIMinimal gameGUI = new GameGUIMinimal(game);

        try {
            gameGUI.setVisible(true);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
