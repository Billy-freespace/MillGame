package com.example.millgame;

import com.example.millgame.pieces.PieceColor;

import java.util.ArrayList;
import javax.swing.*;


public abstract class Piece extends JButton {
    protected PieceColor color;
    protected Position position;
    protected Icon icon;

    public Piece(PieceColor color){ // piece has no defined position
        this.color = color;
        this.position = null;
    }
    public Piece(PieceColor color, Position position){
        this.color = color;
        this.position = position;
    }
    public Piece(PieceColor color, ImageIcon pieceIcon){
        this.color = color;
        this.position = null;
        this.icon = pieceIcon;
    }

    public Piece(PieceColor color, Position position, ImageIcon pieceIcon){
        this.color = color;
        this.position = position;
        this.icon = pieceIcon;
    }

    public void remove(){
        if(position == null){
            // PIECE WAS DELETED OR WAS NOT PLACED TO BOARD
            // RAISE AN EXCEPTION
        }
        position.setPiece(null);
        position = null;
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
}