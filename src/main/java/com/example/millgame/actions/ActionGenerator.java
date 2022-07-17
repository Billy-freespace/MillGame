
package com.example.millgame.actions;

import com.example.millgame.MillGame;
import com.example.millgame.Position;
import com.example.millgame.misc.BoardCoordinate;

/**
 * ActionGenerator
 */
public class ActionGenerator {

    private Millgame game;
    public ActionGenerator (MillGame game) {
        this.game = game;
    }
    public  ActionEvent placePiece(BoardCoordinate coordinate) {
        char x = coordinate.getX();
        int y = coordinate.getY();
        Position position = game.getPosition(x, y);
        ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
        return event;
    }

    public  ActionEvent movePiece(Piece piece, BoardCoordinate coordinate) {
        char x = coordinate.getX();
        int y = coordinate.getY();
        Position position = game.getPosition(x, y);
        ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
        return event;
    }

    public  ActionEvent removePiece(BoardCoordinate coordinate) {
        char x = coordinate.getX();
        int y = coordinate.getY();
        Position position = game.getPosition(x, y);
        ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
        return event;
    }
}