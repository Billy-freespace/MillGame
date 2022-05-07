package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Player;
import com.example.millgame.pieces.PieceColor;

public class RobotPlayer extends Player {
    private PlayerLevel level;

    public RobotPlayer(PieceColor color, Board board)
    {
        super(PlayerType.ROBOT, color, board);
        level = PlayerLevel.NOOB;
    }
    public RobotPlayer(PieceColor color, Board board, PlayerLevel level)
    {
        super(PlayerType.ROBOT, color, board);
        this.level = level;
    }
}
