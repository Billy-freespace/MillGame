package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Player;
import com.example.millgame.RobotPlayerLevel;

class RobotPlayer extends Player {
    private RobotPlayerLevel level;

    RobotPlayer(PieceColor color, Board board)
    {
        super(color, board);
        level = RobotPlayerLevel.EASY;
    }
    RobotPlayer(PieceColor color, Board board, RobotPlayerLevel level)
    {
        super(color, board);
        this.level = level;
    }
}
