package com.example.millgame;

import java.util.List;

interface MillGameBuilderInterface {
    public void reset();
    public void setGameMode(GameMode mode);
    public void setRobotPlayerDificulty(RobotPlayerLevel level);
    public void addPlayer(PieceColor color, PlayerType type);
    public void buildBoard(GameVariant variant);
}

public class MillGameBuild implements MillGameBuilderInterface {
    private MillGame game;
    private Board board;
    private GameMode mode;
    private RobotPlayerLevel robotDificulty;
    private List<Player> players;

    public void reset(){
        game = new MillGame();
    }

    public void setGameMode(GameMode mode){
        this.mode = mode;
    }

    public void setRobotPlayerDificulty(RobotPlayerLevel level){
        this.robotDificulty = level;
    }

    public void buildBoard(GameVariant variant){
        board = BoardCreatorDirector.makeMMBoard(variant);
        game.setBoard(board);
    }


    public void addPlayer(PieceColor color, PlayerType type)
    {
        Player player;

        if(type == PlayerType.HUMAN)
            player = new HumanPlayer(color, board);
        else
            player = new RobotPlayer(color, board, robotDificulty);

        players.add(player);
        game.addPlayer(player);
    }

    public MillGame getResult(){
        TurnIterator turniter = new TurnIterator(players);
        game.setTurnIterator(turniter);

        return game;
    }
}
