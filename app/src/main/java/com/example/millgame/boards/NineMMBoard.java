package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.Mill;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Position;

import java.util.HashMap;

public class NineMMBoard extends Board {
    public NineMMBoard() {
        super(GameVariant.NINE_MEN_MORRIS);
    }

    @Override
    public boolean isValidMill(Mill mill) {
        return true;
    }
}
