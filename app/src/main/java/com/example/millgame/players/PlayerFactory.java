package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.Player;

public abstract class PlayerFactory {
    public abstract Player create(PieceColor color, MillGame game);
    public abstract Player create(PieceColor color, Board board);
    public abstract Player createByLevel(PieceColor color, MillGame game, PlayerLevel level);
    public abstract Player createByLevel(PieceColor color, Board board, PlayerLevel level);

    static public Player create(PlayerType playerType, PieceColor color, Board board){
        Player player;

        switch (playerType){
            case HUMAN:
                player = new HumanPlayerFactory().create(color, board);
                break;
            case ROBOT:
                player = new RobotPlayerFactory().create(color, board);
                break;
            default:
                player = null;
                break;
        }

        return player;
    }

    static public Player create(PlayerType playerType, PieceColor color, MillGame game){
        Player player;

        switch (playerType){
            case HUMAN:
                player = new HumanPlayerFactory().create(color, game);
                break;
            case ROBOT:
                player = new RobotPlayerFactory().create(color, game);
                break;
            default:
                player = null;
                break;
        }

        return player;
    }
}
