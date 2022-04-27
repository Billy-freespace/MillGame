package com.example.millgame;

public interface PlayerFactory {
    public Player create(PieceColor color, Board board);
}

public class HumanPlayerFactory implements PlayerFactory{
    public Player create(PieceColor color, Board board){};
}


public class RobotPlayerFactory implements PlayerFactory{
    public Player create(PieceColor color, Board board){
        return create(color, board, RobotPlayerLevel.EASY);
    }
    public Player create(PieceColor color, Board board, RobotPlayerLevel level){}
}