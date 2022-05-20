package com.example.millgame.actions;

import com.example.millgame.*;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.util.logging.Level;

public class RemovingEventAction extends EventAction {

    @Override
    public void actionPerformed(ActionEvent event){
        Position position = (Position) event.getSource();

        // REMOVE THIS CODE - ADDED FOR TESTING PURPOSES
        // BEGIN
        TraceLogger.log(Level.INFO, position + " was selected", RemovingEventAction.class);
        // END

        try {
            Player opponent = game.getOpponentPlayer();
            opponent.removePiece(position);

            if(opponent.getPlacedPieces() < game.getNumberPlayerPieces()){
                game.changeEventAction(new PositioningEventAction());
            } else { // all pieces were already placed
                int count = opponent.countBoardPieces();
                if(count == 2){
                    TraceLogger.log(Level.INFO, "GAME OVER - winner: " + game.getActivePlayer());
                } else {
                    game.changeEventAction(new MovingEventAction());
                }
            }

            game.nextTurn();

        } catch (Exception error){
            RankedException exception = new RankedException(error, Level.WARNING);
            TraceLogger.log(exception, RemovingEventAction.class);
        }
    }
}