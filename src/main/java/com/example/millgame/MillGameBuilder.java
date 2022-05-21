package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.actions.PositioningEventAction;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;
import com.example.millgame.misc.Color;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.RobotLevel;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotPlayerFactory;


import java.util.logging.Level;

public class MillGameBuilder {
    private MillGame game;
    private Board board;
    private RobotLevel robotLevel;
    private boolean randomTurn;
    private GameMode gameMode;
    private GameVariant variant;

    public MillGameBuilder(GameVariant variant){
        this.variant = variant;
    }

    public MillGameBuilder reset(){
        game = new MillGame(variant);
        board = null;
        randomTurn = false;
        TraceLogger.log(Level.INFO, "Reset MillGame, Players and Board objects", MillGameBuilder.class);

        return this;
    }

    public MillGameBuilder buildBoard(){
        board = BoardCreatorDirector.makeMMBoard(variant);
        game.setBoard(board);

        return this;
    }


    public MillGameBuilder setTurnTime(int seconds){

        return this;
    }

    public MillGameBuilder setGameMode(GameMode mode){
        gameMode = mode;

        return this;
    }
    public MillGameBuilder setRobotLevel(RobotLevel level){
        robotLevel = level;

        return this;
    }

    public MillGameBuilder createPlayer(PlayerType playerType, Color color) throws RankedException{
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

    public MillGame build() throws RankedException {
        game.initTurn(randomTurn);
        // initialize event action
        game.changeEventAction(new PositioningEventAction());

        return game;
    }
}
