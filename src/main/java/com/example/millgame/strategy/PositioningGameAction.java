package com.example.millgame.strategy;

import com.example.millgame.MillGame;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.misc.BoardCoordinate;

import java.awt.event.ActionEvent;

public class PositioningGameAction extends GameAction {
    private ActionEvent actionEvent;

    public PositioningGameAction(MillGame game, Position position) throws InvalidPositionCoordinate {
        super(game);
        actionEvent = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece [" + position + "]");
    }

    @Override
    public void performActions() {
        performAction(actionEvent);
    }
}
