package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Player;
import com.example.millgame.Piece.PieceColor;
import com.example.millgame.Player.PlayerLevel;

class RobotPlayer extends Player {
    private PlayerLevel level;

    RobotPlayer(PieceColor color, Board board)
    {
        super(color, board);
        level = PlayerLevel.NOOB;
    }
    RobotPlayer(PieceColor color, Board board, PlayerLevel level)
    {
        super(color, board);
        this.level = level;
    }
}
