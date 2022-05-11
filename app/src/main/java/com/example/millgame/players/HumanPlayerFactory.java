package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.pieces.PieceColor;

public class HumanPlayerFactory extends PlayerFactory {
    //private HumanPlayer humanPlayer;
    //private PieceColor pieceColor;
    //private Board board;
    //private PlayerLevel level;


    public Player create(PieceColor color, MillGame game) {
        return new HumanPlayer(color, game);
    }

    public Player create(PieceColor color, Board board) {
        return new HumanPlayer(color, board);
    }

    public Player createByLevel(PieceColor color, MillGame game, PlayerLevel level) { return null; }
    public Player createByLevel(PieceColor color, Board board, PlayerLevel level) { return null; }
}
