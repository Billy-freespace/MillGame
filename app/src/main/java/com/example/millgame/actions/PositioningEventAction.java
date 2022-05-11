package com.example.millgame.actions;

import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.EventException;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.util.logging.Level;

public class PositioningEventAction extends EventAction {
    @Override
    public void actionPerformed(ActionEvent event) {
        Position position = (Position) event.getSource();

        // REMOVE THIS CODE - ADDED FOR TESTING PURPOSES
        // BEGIN
        TraceLogger.log(Level.INFO, position + " was selected", PositioningEventAction.class);
        // END

        try{
            if(position.hasPiece()){
                 throw new EventException(event, "Selected position has a piece - select an empty position");
            }

            Player player = game.getActivePlayer();
            player.placePiece(position);


            // CHECK IF A MILL WAS FORMED
            if(false){ // sprint 2 - verify if a mill was formed
                // HIGHLIGHT POSSIBLE POSITIONS TO DELETE
                game.changeEventAction(new RemovingEventAction());
            } else {
                Player opponent = game.getOpponentPlayer();
                if(player.getPlacedPieces() == player.npieces &&
                        opponent.getPlacedPieces() == opponent.npieces){
                    // both players have positioned all their pieces, so change event action to Moving
                    // for the next play
                    game.changeEventAction(new MovingEventAction());
                }
                game.nextTurn();
            }

        } catch (InvalidPositionCoordinate | EventException error){
            TraceLogger.log(error, PositioningEventAction.class);
        }
        catch (NoPiecesError error){
            // the player was positioned all their pieces, so now it will move them
            game.changeEventAction(new MovingEventAction());
        }
        // REMOVE THIS CODE - ADDED FOR TESTING PURPOSES
        // BEGIN
        catch (NullPointerException error){
            // the player was positioned all their pieces, so now it will move them
            System.out.println("ERROR NULLPOINTER EXCEPTION");
        }
        // END
    }
}
