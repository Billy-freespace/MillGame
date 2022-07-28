
package com.example.millgame.actions;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.misc.BoardCoordinate;

import java.awt.event.ActionEvent;

/**
 * ActionGenerator
 */
public class ActionGenerator {

  private MillGame game;
  public ActionGenerator (MillGame game) {
    this.game = game;
  }
  public ActionEvent placePiece(BoardCoordinate coordinate) throws InvalidPositionCoordinate {
    char x = coordinate.getX();
    int y = coordinate.getY();
    Position position = game.getBoard().getPosition(x, y);
    ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
    return event;
  }

  public  ActionEvent movePiece(Piece piece, BoardCoordinate coordinate) throws InvalidPositionCoordinate {
    char x = coordinate.getX();
    int y = coordinate.getY();
    Position position = game.getBoard().getPosition(x, y);
    ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
    return event;
  }

  public  ActionEvent removePiece(BoardCoordinate coordinate) throws InvalidPositionCoordinate {
    char x = coordinate.getX();
    int y = coordinate.getY();
    Position position = game.getBoard().getPosition(x, y);
    ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
    return event;
  }
}