package com.example.millgame.actions;

import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;

import java.awt.event.ActionEvent;

public class PositioningEventAction extends EventAction {
    @Override
    public void performAction(ActionEvent event) {
        Position position = (Position) event.getSource();
        try{
            if(!position.hasPiece()){
                Player player = game.getActivePlayer();
                player.placePiece(position); // LOG EXCEPTION
            }

            // CHECK IF A MILL WAS FORMED
            // HIGHLIGHT POSIBLE POSITIONS TO DELETE
            // IF A MILL WAS FORMED CHANGE OF EVENT ACTION TO REMOVING
            game.changeEventAction(new RemovingEventAction());
        } catch (InvalidPositionCoordinate error){
            // LOG ERROR
            System.out.println(error.getMessage());
        }
    }
}
