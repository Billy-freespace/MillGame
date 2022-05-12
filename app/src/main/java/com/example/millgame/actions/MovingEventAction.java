package com.example.millgame.actions;

import com.example.millgame.Piece;
import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.exceptions.EventException;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.util.logging.Level;

public class MovingEventAction extends EventAction {
    private Position selectedPosition = null;

    @Override
    public void actionPerformed(ActionEvent event) {
        Position position = (Position) event.getSource();

        // REMOVE THIS CODE - ADDED FOR TESTING PURPOSES
        // BEGIN
        TraceLogger.log(Level.INFO, position + " was selected", MovingEventAction.class);
        // END


        try {
            // forced throw exception
            throw new RankedException(MovingEventAction.class.getName() + " was not implemented yet! (SPRINT 2)");

            /*
            if(selectedPosition == null){
                if(!position.hasPiece()){
                    throw new EventException(event,
                            "Selected a piece - empty position was selected", Level.INFO);
                }
                selectedPosition = position;
            }
            else {
                if (position.hasPiece()) {
                    throw new EventException(event,
                            "Selected position has a piece - select an empty position", Level.INFO);
                }

                Piece piece = position.getPiece();
                Player player = game.getActivePlayer();
                player.movePiece(piece, position);

                // CHECK IF A MILL WAS FORMED
                // IF A MILL WAS FORMED CHANGE OF EVENT ACTION TO REMOVING
                game.changeEventAction(new RemovingEventAction());

                //ELSE
                selectedPosition = null;
                // END IF-ELSE

                game.nextTurn();
            }
             */
        } catch (InvalidPositionCoordinate error) {
            TraceLogger.log(error, MovingEventAction.class);
        } catch (EventException error) {
            TraceLogger.log(error, MovingEventAction.class);
        } catch (RankedException error) {
            TraceLogger.log(error);
        }
    }
}
