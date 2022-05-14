package com.example.millgame.actions;

import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.*;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.util.logging.Level;

public class PositioningEventAction extends EventAction {
    @Override
    public void actionPerformed(ActionEvent event) {
        Position position = (Position) event.getSource();

        TraceLogger.log(Level.FINE, position + " was selected", PositioningEventAction.class);

        try{
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

        } catch (InvalidPositionCoordinate | NotEmptyPosition error) {
            TraceLogger.log(error, PositioningEventAction.class);
        } catch (NoPiecesError error){
            // the player was positioned all their pieces, so now it will move them
            TraceLogger.log(error, PositioningEventAction.class);
            game.changeEventAction(new MovingEventAction());
        }
    }
}
