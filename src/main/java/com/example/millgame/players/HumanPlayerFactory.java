package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.misc.Color;

public class HumanPlayerFactory extends PlayerFactory {

  public Player create(Color color, MillGame game) {
    return new HumanPlayer(color, game);
  }

  public Player createByLevel(Color color, MillGame game, RobotLevel level) { return create(color, game); }
}
