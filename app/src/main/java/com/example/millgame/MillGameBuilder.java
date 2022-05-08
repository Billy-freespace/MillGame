package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.actions.PositioningEventAction;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;
import com.example.millgame.logging.TraceMode;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.HumanPlayer;
import com.example.millgame.players.PlayerLevel;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotPlayer;

import java.util.ArrayList;
import java.util.logging.Level;

interface MillGameBuilderInterface {
    public void reset();
    public void buildBoard(GameVariant variant);
    public void setGameMode(GameMode mode);
    public void setRobotLevel(PlayerLevel level);
    public void addPlayer(PieceColor color, PlayerType type);
}

public class MillGameBuilder implements MillGameBuilderInterface {
    private MillGame game;
    private Board board;
    private GameMode mode;
    private PlayerLevel robotLevel = PlayerLevel.NOOB;
    private ArrayList<Player> players;

    public void reset(){
        game = new MillGame();
        players = new ArrayList<Player>(2);
        board = null;
        TraceLogger.log(Level.INFO, "Reset MillGame, Players and Board objects", MillGameBuilder.class);
    }

    public void buildBoard(GameVariant variant){
        board = BoardCreatorDirector.makeMMBoard(variant);
        game.setBoard(board);
    }

    public void setGameMode(GameMode mode){
        this.mode = mode;
    }

    public void setRobotLevel(PlayerLevel level){
        robotLevel = level;
    } // sprint 2

    public void addPlayer(PieceColor color, PlayerType type)
    {
        Player player;

        if(type == PlayerType.HUMAN)
            player = new HumanPlayer(color, game);
        else
            player = new RobotPlayer(color, game, robotLevel);

        TraceLogger.log(Level.INFO, "Player " + player + " was created", MillGameBuilder.class);

        players.add(player);
    }

    public MillGame build(GameVariant variant, GameMode gameMode){
        TraceMessage traceMessage = new TraceMessage(Level.INFO,
                "Building MillGame: GameVariant=" +
                variant + ", GameMode=" + gameMode,
                MillGameBuilder.class);
        TraceLogger.log(traceMessage);
        reset();
        buildBoard(variant);
        setGameMode(gameMode);

        // create 2 players
        addPlayer(PieceColor.WHITE, PlayerType.HUMAN);
        PieceColor opponentColor = PieceColor.BLACK;

        if(mode == GameMode.HUMAN_ROBOT){
            addPlayer(opponentColor, PlayerType.ROBOT);
        }
        else{
            addPlayer(opponentColor, PlayerType.HUMAN);
        }

        TurnIterator itr = new TurnIterator(players);
        game.setTurnIterator(itr);
        game.setPlayers(players);
        game.changeEventAction(new PositioningEventAction());

        return game;
    }
}
