package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("No X11 enable")
public class GameGUIMinimalTest {
    @Test
    public void emptyNineMMBoardTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        GameGUIMinimal gameGUI = new GameGUIMinimal(variant, mode);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initializedNineMMBoardTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        MillGame game = new MillGameBuilder().build(variant, mode);

        // initialization of game
        Player player = game.getActivePlayer();
        player.placePiece('a', 1);

        game.nextTurn();

        player = game.getActivePlayer();
        player.placePiece('b', 2);

        game.nextTurn();

        GameGUIMinimal gameGUI = new GameGUIMinimal(game);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void twelveMMBoardPanelTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;
        GameGUIMinimal game = new GameGUIMinimal(variant, mode);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sixMMBoardPanelTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.SIX_MEN_MORRIS;
        GameGUIMinimal game = new GameGUIMinimal(variant, mode);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
