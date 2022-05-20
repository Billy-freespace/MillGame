package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;
import com.example.millgame.misc.Color;

public class HumanPlayer extends Player {
    public HumanPlayer(Color color, MillGame game) {
        super(PlayerType.HUMAN, color, game);
    }

    public HumanPlayer(Color color, Board board){
        super(PlayerType.HUMAN, color, board);
    }
}