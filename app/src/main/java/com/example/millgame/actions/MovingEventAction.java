package com.example.millgame.actions;

import com.example.millgame.Piece;
import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.EventException;

import java.awt.event.ActionEvent;

public class MovingEventAction extends EventAction {
    private Position selectedPosition = null;

    @Override
    public void performAction(ActionEvent event) {
        Position position = (Position) event.getSource();

        try {
            if(selectedPosition == null){
                if(!position.hasPiece()){
                    throw new EventException(event, "Selected a piece - empty position was selected");
                }
                selectedPosition = position;
            }
            else {
                if (position.hasPiece()) {
                    throw new EventException(event, "Selected position has a piece - select an empty position");
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
        } catch (InvalidPositionCoordinate error){
            // LOG ERROR
            System.out.println(error.getMessage());
        }
        catch (EventException error){
            // LOG ERROR
            System.out.println(error.getMessage());
        }
    }
}
