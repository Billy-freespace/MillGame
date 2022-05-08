package com.example.millgame.actions;

import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.EventException;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;

public class PositioningEventAction extends EventAction {
    @Override
    public void performAction(ActionEvent event) {
        Position position = (Position) event.getSource();
        try{
            if(position.hasPiece()){
                 throw new EventException(event, "Selected position has a piece - select an empty position");
            }

            Player player = game.getActivePlayer();
            player.placePiece(position); // CHECK NoPiecesError exception

            // CHECK IF A MILL WAS FORMED
            // HIGHLIGHT POSSIBLE POSITIONS TO DELETE
            // IF A MILL WAS FORMED CHANGE OF EVENT ACTION TO REMOVING
            game.changeEventAction(new RemovingEventAction());
            // ELSE
            game.nextTurn();
            // END ELSE-IF

        } catch (InvalidPositionCoordinate error){
            TraceLogger.log(error, PositioningEventAction.class);
        }
        catch (EventException error){
            TraceLogger.log(error, PositioningEventAction.class);
        }
    }
}
