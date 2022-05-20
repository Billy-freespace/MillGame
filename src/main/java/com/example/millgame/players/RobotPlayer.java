package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;
import com.example.millgame.pieces.PieceColor;

public class RobotPlayer extends Player {
    private PlayerLevel level;

    public RobotPlayer(PieceColor color, MillGame game)
    {
        super(PlayerType.ROBOT, color, game);
        level = PlayerLevel.NOOB;
    }

    public RobotPlayer(PieceColor color, Board board)
    {
        super(PlayerType.ROBOT, color, board);
        level = PlayerLevel.NOOB;
    }

    public RobotPlayer(PieceColor color, MillGame game, PlayerLevel level)
    {
        super(PlayerType.ROBOT, color, game);
        this.level = level;
    }
}