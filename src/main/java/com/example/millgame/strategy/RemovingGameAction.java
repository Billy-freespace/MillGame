package com.example.millgame.strategy;

import com.example.millgame.MillGame;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.misc.BoardCoordinate;

import java.awt.event.ActionEvent;

public class RemovingGameAction extends GameAction {
    private ActionEvent actionEvent;

    public RemovingGameAction(MillGame game, BoardCoordinate boardCoordinate) throws InvalidPositionCoordinate {
        super(game);
        char x = boardCoordinate.getX();
        int y = boardCoordinate.getY();
        Position position = game.getBoard().getPosition(x, y);
        actionEvent = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece [" + position + "]");
    }

    @Override
    public void performActions() {
        performAction(actionEvent);
    }
}
