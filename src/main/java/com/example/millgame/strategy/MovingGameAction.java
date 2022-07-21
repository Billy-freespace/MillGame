package com.example.millgame.strategy;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.misc.BoardCoordinate;

import java.awt.event.ActionEvent;

public class MovingGameAction extends GameAction {
    private ActionEvent selectPiece;
    private ActionEvent selectEmptyPosition;

    public MovingGameAction(MillGame game, Piece piece, BoardCoordinate boardCoordinate) throws InvalidPositionCoordinate {
        super(game);
        char x = boardCoordinate.getX();
        int y = boardCoordinate.getY();
        Position initialPosition = piece.getPosition();
        Position finalPosition = game.getBoard().getPosition(x, y);
        selectPiece = new ActionEvent(initialPosition, -1, "RobotPlayer.autoPlacePiece [" + initialPosition + "]");
        selectEmptyPosition = new ActionEvent(finalPosition, -1, "RobotPlayer.autoPlacePiece [" + finalPosition + "]");
    }

    @Override
    public void performActions() {
        performAction(selectPiece);
        performAction(selectEmptyPosition);
    }
}
