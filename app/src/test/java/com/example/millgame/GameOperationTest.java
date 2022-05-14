package com.example.millgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameOperationTest {

    private MillGame game;
    private Board board;

    @BeforeEach
    public void initGame(){
        MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
        MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;

        game = new MillGameBuilder().build(variant, mode);
        board = game.getBoard();
    }

    /*
     * Tests for history 3 (Colocar una pieza)
     */

    @Test
    public void placePieceTest(){
        /*
         * Test for AC3.1
         */
        int i = 1;
    }


    /*
     * Tests for history 4 (Eliminar pieza del oponente)
     */
    
    @Test
    public void SOMETHING_H4(){
        /*
         * Test for AC4.1
         */
    }


    /*
     * Tests for history 5 (Mover pieza)
     */

    @Test
    public void SOMETHING_H5(){
        /*
         * Test for AC5.1
         */
    }
}
