package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.actions.PositioningEventAction;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;
import com.example.millgame.misc.Color;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.RobotLevel;
import com.example.millgame.players.PlayerType;


import java.util.logging.Level;

public class MillGameBuilder {
    private MillGame game;
    private Board board;
    private GameMode mode;
    private RobotLevel robotLevel = RobotLevel.NOOB;

    public void reset(GameVariant variant){
        game = new MillGame(variant);
        board = null;
        TraceLogger.log(Level.INFO, "Reset MillGame, Players and Board objects", MillGameBuilder.class);
    }

    public void buildBoard(GameVariant variant){
        board = BoardCreatorDirector.makeMMBoard(variant);
        game.setBoard(board);
    }

    public void setRobotLevel(RobotLevel level){
        robotLevel = level;
    } // sprint 2

    public MillGame build(GameVariant variant, GameMode gameMode) throws RankedException {
        TraceMessage traceMessage = new TraceMessage(Level.INFO,
                "Building MillGame: GameVariant=" +
                variant + ", GameMode=" + gameMode,
                MillGameBuilder.class);
        TraceLogger.log(traceMessage);

        reset(variant);
        buildBoard(variant);

        // create 2 players
        //TraceLogger.log(Level.INFO,"GAME: "+ game);
        game.addPlayer(PlayerType.HUMAN, Color.WHITE);
        Color opponentColor = Color.BLACK;
        PlayerType opponentType = PlayerType.HUMAN;

        if(gameMode == GameMode.HUMAN_ROBOT){
            opponentType = PlayerType.ROBOT;
        }

        game.addPlayer(opponentType, opponentColor);


        // initialize event action
        game.changeEventAction(new PositioningEventAction());
        game.initTurn(false);

        //TraceLogger.log(Level.INFO, "MIllGameBuilder.build() was build game: "+ game);

        return game;
    }
}
