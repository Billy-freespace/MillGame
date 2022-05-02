package com.example.millgame;

import java.util.ArrayList;
import java.util.List;


public class Piece {
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
    public List<Position> getEmptyNeighbours(){
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
}
