package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Player;
import com.example.millgame.misc.Color;

public class RobotPlayer extends Player {
    private RobotLevel level;

    public RobotPlayer(Color color, MillGame game)
    {
        super(PlayerType.ROBOT, color, game);
        level = RobotLevel.NOOB;
    }

    public RobotPlayer(Color color, MillGame game, RobotLevel level)
    {
        super(PlayerType.ROBOT, color, game);
        this.level = level;
    }
}
