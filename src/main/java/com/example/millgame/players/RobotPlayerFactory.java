package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.misc.Color;

public class RobotPlayerFactory extends PlayerFactory{
    public Player create(Color color, MillGame game){ return new NoobRobotPlayer(color, game); }
    public Player createByLevel(Color color, MillGame game, RobotLevel level) {
        RobotPlayer player;
        switch (level){
            case NOOB:
                player = new NoobRobotPlayer(color, game);
                break;
            case NINJA:
                player = new NinjaRobotPlayer(color, game);
                break;
            default:
                player= null;
        }

        return player;
    }
}
