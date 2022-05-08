package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.pieces.PieceColor;

public class HumanPlayerFactory implements PlayerFactory {
    //private HumanPlayer humanPlayer;
    //private PieceColor pieceColor;
    //private Board board;
    //private PlayerLevel level;

    @Override
    public Player create(PieceColor color, MillGame game) {
        return new HumanPlayer(color, game);
    }
    
    @Override
    public Player createByLevel(PieceColor color, MillGame game, PlayerLevel level) {
        return null;
    }
}
