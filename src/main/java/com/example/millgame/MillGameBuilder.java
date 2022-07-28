package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.actions.PositioningEventAction;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Color;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.RobotLevel;
import com.example.millgame.players.PlayerType;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MillGameBuilder {
  private MillGame game;
  private Board board;
  private RobotLevel robotLevel;
  private boolean randomTurn = false;
  private GameVariant variant;

  private List<Color> playerColors;

  public MillGameBuilder(GameVariant variant){
    this.playerColors = getDefaultColors();
    this.variant = variant;
  }

  public MillGameBuilder(GameVariant variant, List<Color> playerColors) throws RankedException {
    if(playerColors.size() != 2){
      throw new RankedException("Invalid number of player colors");
    }

    this.playerColors = playerColors;
    this.variant = variant;
  }

  private List<Color> getDefaultColors(){
    List<Color> defaultColors = new ArrayList<Color>(2);
    defaultColors.add(Color.WHITE);
    defaultColors.add(Color.BLACK);

    return defaultColors;
  }

  public MillGameBuilder reset() {
    game = new MillGame(variant);
    board = null;
    randomTurn = false;
    TraceLogger.log(Level.INFO, "Reset MillGame, Players and Board objects", MillGameBuilder.class);

    return this;
  }

  public MillGameBuilder buildBoard(){
    board = BoardCreatorDirector.makeMMBoard(variant, playerColors);
    game.setBoard(board);

    System.out.println("GAME: "+ game);
    return this;
  }

  public MillGameBuilder setRobotLevel(RobotLevel level){
    robotLevel = level;

    return this;
  }

  public MillGameBuilder createPlayers(GameMode mode) throws RankedException {
    return createPlayers(mode, playerColors);
  }

  public MillGameBuilder createPlayers(GameMode mode, List<Color> colors) throws RankedException{
    if(colors.size() < 2){
      throw new RankedException("Needed 2 player color. Supplied: " + colors.size());
    }
    Color color = colors.get(0);
    createPlayer(PlayerType.HUMAN, color);

    PlayerType playerType = PlayerType.HUMAN;
    if(mode == GameMode.HUMAN_ROBOT){
      playerType = PlayerType.ROBOT;
    }

    color = colors.get(1);
    createPlayer(playerType, color);

    return this;
  }

  private MillGameBuilder createPlayer(PlayerType playerType, Color color) throws RankedException{
    Player player;

    if(playerType == PlayerType.ROBOT){
      player = PlayerFactory.createRobot(color, game, robotLevel);

    } else {
      player = PlayerFactory.createHuman(color, game);
    }

    game.addPlayer(player);

    return this;
  }

  public MillGameBuilder setRandomTurn(boolean random){
    randomTurn = random;

    return this;
  }

  public MillGameBuilder initTurnIterator(){
    game.initTurnIterator(randomTurn);

    return this;
  }

  public MillGame build() throws RankedException {
    // initialize event action
    game.changeEventAction(new PositioningEventAction());

    // initialize turn iterator
    game.nextTurn();

    return game;
  }
}
