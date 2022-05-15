package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import org.junit.jupiter.api.Test;

public class BoardPanelTest {
    @Test
    public void nineMMBoardPanelTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        GameGUIMinimal game = new GameGUIMinimal(variant, mode);
        game.setVisible(true);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void twelveMMBoardPanelTest() throws RankedException {
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;
        MillGame.GameVariant variant = MillGame.GameVariant.TWELVE_MEN_MORRIS;
        GameGUIMinimal game = new GameGUIMinimal(variant, mode);
        game.setVisible(true);

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
        game.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
