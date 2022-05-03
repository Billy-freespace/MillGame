package com.example.millgame;

import com.example.millgame.MillGame.GameMode;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Player.PlayerLevel;
import com.example.millgame.Player.PlayerType;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.boards.BoardCreatorDirector;
import com.example.millgame.players.HumanPlayer;
import com.example.millgame.players.RobotPlayer;

import java.util.ArrayList;
import java.util.List;

interface MillGameBuilderInterface {
    public void reset();
    public void setGameMode(GameMode mode);
    public void setRobotPlayerDifficulty(PlayerLevel level);
    public void addPlayer(PieceColor color, PlayerType type);
    public void buildBoard(GameVariant variant);
}

public class MillGameBuilder implements MillGameBuilderInterface {
    private MillGame game;
    private Board board;
    private GameMode mode;
    private PlayerLevel robotDifficulty = PlayerLevel.NOOB;
    private ArrayList<Player> players;

    public void reset(){
        game = new MillGame();
        players = new ArrayList<Player>();
    }

    public void setGameMode(GameMode mode){
        this.mode = mode;
    }

    public void setRobotPlayerDifficulty(PlayerLevel level){
        this.robotDifficulty = level;
    }

    public void buildBoard(GameVariant variant){
        board = BoardCreatorDirector.makeMMBoard(variant);
        game.setBoard(board);
    }

    /*
    public void createPlayers(GameMode mode)
    {
        players.add(new HumanPlayer(PieceColor.WHITE, board));
        if (mode == GameMode.HUMAN_HUMAN){

        }
        else {

        }

    }
     */

    public void addPlayer(PieceColor color, PlayerType type)
    {
        Player player;

        if(type == PlayerType.HUMAN)
            player = new HumanPlayer(color, board);
        else
            player = new RobotPlayer(color, board, robotDifficulty);

        players.add(player);
    }

    public MillGame getResult(){
        TurnIterator itr = new TurnIterator(players);
        game.setTurnIterator(itr);
        game.setPlayers(players);
        return game;
    }
}
