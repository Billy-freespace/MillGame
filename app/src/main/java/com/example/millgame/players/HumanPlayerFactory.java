package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Player;

import com.example.millgame.pieces.PieceColor;

public class HumanPlayerFactory implements PlayerFactory {
    //private HumanPlayer humanPlayer;
    //private PieceColor pieceColor;
    //private Board board;
    //private PlayerLevel level;

    @Override
    public Player create(PieceColor color, Board board) {
        return new HumanPlayer(color, board);
    }
    
    @Override
    public Player createByLevel(PieceColor color, Board board, PlayerLevel level) {
        return null;
    }
}
