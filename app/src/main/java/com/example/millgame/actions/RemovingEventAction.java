package com.example.millgame.actions;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;

import java.awt.event.ActionEvent;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.lang.CloneNotSupportedException;

public class RemovingEventAction extends EventAction {

    private ArrayList<Position> positions; // possible positions to delete


    @Override
    public void performAction(ActionEvent event){
        Position position = (Position) event.getSource();
        try {
            if(positions.contains(position)){
                Player opponent = game.getOpponentPlayer();
                Piece piece = position.getPiece();
                opponent.removePiece(piece);

                // REDRAW GAME BOARD
            }
        } catch (CloneNotSupportedException error){
            // LOG ERROR
            System.out.println(error.getMessage());
        }

    }
}