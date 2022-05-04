package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.HumanPlayer;
import com.example.millgame.players.PlayerLevel;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotPlayer;

import java.util.ArrayList;

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
        players = new ArrayList<Player>();
        board = null;
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
    }

    public void addPlayer(PieceColor color, PlayerType type)
    {
        Player player;

        if(type == PlayerType.HUMAN)
            player = new HumanPlayer(color, board);
        else
            player = new RobotPlayer(color, board, robotLevel);

        players.add(player);
    }

    public MillGame build(GameVariant variant){
        reset();
        buildBoard(variant);

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

        return game;
    }
}
