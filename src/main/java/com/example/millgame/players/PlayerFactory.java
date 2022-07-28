package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.misc.Color;
import com.example.millgame.Player;

public abstract class PlayerFactory {
  public abstract Player create(Color color, MillGame game);
  public abstract Player createByLevel(Color color, MillGame game, RobotLevel level);

  static public Player createHuman(Color color, MillGame game){
    Player player;
    player = new HumanPlayerFactory().create(color, game);

    return player;
  }

  static public Player createRobot(Color color, MillGame game, RobotLevel level){
    Player player;
    player = new RobotPlayerFactory().createByLevel(color, game, level);

    return player;
  }
}
