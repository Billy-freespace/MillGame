package com.example.millgame.actions;

import com.example.millgame.Board;
import com.example.millgame.Piece;
import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.*;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;

public class MovingEventAction extends EventAction {
    private Position selectedPosition = null;

    public MovingEventAction(){super(ActionType.MOVING);}

    @Override
    public void actionPerformed(ActionEvent event) {
        Position position = (Position) event.getSource();
        Piece piece;
        TraceLogger.log(Level.INFO, position + " was selected", MovingEventAction.class);

        try {
            Player player = game.getActivePlayer();

            if(game.isGameOver()){
                throw new GameOverError(player, position, MovingEventAction.class);
            }

            if(selectedPosition == null){
                if(!position.hasPiece()){
                    throw new EmptyPositionError(position);
                }

                piece = position.getPiece();
                if(!player.hasPiece(piece)) { // tying to manipulate opponent piece
                    throw new NotOwnPiece(piece);
                }

                selectedPosition = position;
                TraceLogger.log(Level.INFO, "Selected position: " + position, MovingEventAction.class);
            }
            else {
                piece = position.getPiece();
                if (piece != null) {
                    if(player.hasPiece(piece)){ // a new piece of the player was selected (SWITCH)
                        selectedPosition = position;
                        TraceLogger.log(Level.INFO, "Selected position (SWITCH): " + position, MovingEventAction.class);
                    } else {
                        // trying to move a piece on opponent pieces
                        throw new NotEmptyPosition(position);
                    }

                } else { // the position is empty, so move the selected piece
                    piece = selectedPosition.getPiece();
                    player.movePiece(piece, position);

                    List<Board.Mill> mills = game.getMills(position.getPiece());
                    if(mills.size() > 0){
                        // HIGHLIGHT POSSIBLE POSITIONS TO DELETE
                        TraceLogger.log(Level.INFO, "mills were formed: " + mills);
                        game.changeEventAction(new RemovingEventAction());
                    } else {
                        selectedPosition = null;
                        game.nextTurn();
                    }
                }
            }
        } catch (InvalidPositionCoordinate | EmptyPositionError | NotEmptyPosition | GameOverError error) {
            TraceLogger.log(error, MovingEventAction.class);
        } catch (RankedException error){ // REMOVE THIS catch (TOO GENERAL)
            TraceLogger.log(error, MovingEventAction.class);
        }
    }
}