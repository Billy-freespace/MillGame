package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.misc.Color;
import com.example.millgame.Player;

public abstract class PlayerFactory {
    public abstract Player create(Color color, MillGame game);
    public abstract Player createByLevel(Color color, MillGame game, RobotLevel level);


    static public Player create(PlayerType playerType, Color color, MillGame game){
        Player player;

        switch (playerType){
            case HUMAN:
                player = new HumanPlayerFactory().create(color, game);
                break;
            case ROBOT:
                player = new RobotPlayerFactory().create(color, game);
                break;
            default:
                player = null;
                break;
        }

        return player;
    }
}
