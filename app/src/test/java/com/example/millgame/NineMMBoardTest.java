package com.example.millgame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NineMMBoardTest {
    @Test
    void testGameVariant() {
        NineMMBoard ninemmboard = new NineMMBoard();
        GameVariant esperado = ninemmboard.getGameVariant();
        GameVariant real = GameVariant.NINE_MEN_MORRIS;
        Assertions.assertEquals(esperado, real);
    }
}