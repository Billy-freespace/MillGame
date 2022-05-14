package com.example.millgame.actions;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.lang.CloneNotSupportedException;
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
            
        } catch (Exception error){
            RankedException exception = new RankedException(error, Level.WARNING);
            TraceLogger.log(exception, RemovingEventAction.class);
        }
    }
}