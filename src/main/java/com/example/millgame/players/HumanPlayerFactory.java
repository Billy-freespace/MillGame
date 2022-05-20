package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.misc.Color;

public class HumanPlayerFactory extends PlayerFactory {
    //private HumanPlayer humanPlayer;
    //private PieceColor pieceColor;
    //private Board board;
    //private PlayerLevel level;


    public Player create(Color color, MillGame game) {
        return new HumanPlayer(color, game);
    }

    public Player create(Color color, Board board) {
        return new HumanPlayer(color, board);
    }

    public Player createByLevel(Color color, MillGame game, PlayerLevel level) { return null; }
    public Player createByLevel(Color color, Board board, PlayerLevel level) { return null; }
}
