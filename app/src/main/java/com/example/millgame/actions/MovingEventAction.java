package com.example.millgame.actions;

import com.example.millgame.Piece;
import com.example.millgame.Player;
import com.example.millgame.Position;

import java.awt.event.ActionEvent;

public class MovingEventAction extends EventAction {
    private Position selectedPosition = null;

    @Override
    public void performAction(ActionEvent event){
        Position position = (Position) event.getSource();

        if(selectedPosition == null){
            if(!position.hasPiece()){
                // LOG - LEVEL = ERROR
                System.out.println("Select a piece");
            }
            else {
                selectedPosition = position;
            }
        }
        else {
            if(position.hasPiece()){
                // LOG - LEVEL = ERROR
                System.out.println("Selected position is not empty");
            }
            else {
                Piece piece = position.getPiece();
                Player player = game.getActivePlayer();
                player.movePiece(piece, position);

                // CHECK IF A MILL WAS FORMED
                // IF A MILL WAS FORMED CHANGE OF EVENT ACTION TO REMOVING
                game.changeEventAction(new RemovingEventAction());
            }
        }
    }
}
