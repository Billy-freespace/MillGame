package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.actions.PositioningEventAction;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.PlayerLevel;
import com.example.millgame.players.PlayerType;


import java.util.logging.Level;

public class MillGameBuilder {
    private MillGame game;
    private Board board;
    private BoardPanel boardPanel;
    private GameMode mode;
    private PlayerLevel robotLevel = PlayerLevel.NOOB;

    public void reset(){
        game = new MillGame();
        board = null;
        boardPanel = null;
        TraceLogger.log(Level.INFO, "Reset MillGame, Players and Board objects", MillGameBuilder.class);
    }

    public void buildBoard(GameVariant variant){
        board = BoardCreatorDirector.makeMMBoard(variant);
        game.setBoard(board);
    }

    public void buildBoardPanel(){ // depends of buildBoard step
        boardPanel = new BoardPanel(board);
        game.setBoardPanel(boardPanel);
    }

    public void setRobotLevel(PlayerLevel level){
        robotLevel = level;
    } // sprint 2

    public MillGame build(GameVariant variant, GameMode gameMode) throws RankedException {
        TraceMessage traceMessage = new TraceMessage(Level.INFO,
                "Building MillGame: GameVariant=" +
                variant + ", GameMode=" + gameMode,
                MillGameBuilder.class);
        TraceLogger.log(traceMessage);

        reset();
        buildBoard(variant);
        buildBoardPanel();

        // create 2 players
        game.addPlayer(PlayerType.HUMAN, PieceColor.WHITE);
        PieceColor opponentColor = PieceColor.BLACK;
        PlayerType opponentType = PlayerType.HUMAN;

        if(gameMode == GameMode.HUMAN_ROBOT){
            opponentType = PlayerType.ROBOT;
        }

        game.addPlayer(opponentType, opponentColor);

        // initialize event action
        game.changeEventAction(new PositioningEventAction());
        game.initTurn(false);

        return game;
    }
}
