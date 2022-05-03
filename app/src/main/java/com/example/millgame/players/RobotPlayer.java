package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Player;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.Player.PlayerLevel;

public class RobotPlayer extends Player {
    private PlayerLevel level;

    public RobotPlayer(PieceColor color, Board board)
    {
        super(color, board);
        level = PlayerLevel.NOOB;
    }
    public RobotPlayer(PieceColor color, Board board, PlayerLevel level)
    {
        super(color, board);
        this.level = level;
    }
}
