package com.example.millgame;

import java.util.ArrayList;
import javax.swing.JButton;


public class Piece extends  JButton{
    private PieceColor color;
    private Position position;


    public Piece(PieceColor color){ // piece has no defined position
        this.color = color;
        this.position = null;
    }
    public Piece(PieceColor color, Position position){
        this.color = color;
        this.position = position;
    }
    public ArrayList<Position> getEmptyNeighbours(){
        ArrayList<Position> emptyNeighbours = new ArrayList<Position>();

        for(Position position : position.getNeighbours()){
            if(position.getPiece() == null)
                continue;
            emptyNeighbours.add(position);
        }

        return emptyNeighbours;
    }
    public void setPosition(Position position){this.position = position;}
    public Position getPosition(){return position;}

    /*
     * Inner enumeration
     */
    public enum PieceColor {
        WHITE,
        BLACK
    }
}


class WritePiece extends Piece {

}

class BlackPiece extends Piece {

}