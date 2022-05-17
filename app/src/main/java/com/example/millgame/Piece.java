package com.example.millgame;

import com.example.millgame.misc.ObjectIcon;
import com.example.millgame.pieces.PieceColor;

import java.util.ArrayList;
import javax.swing.*;


public abstract class Piece implements ObjectIcon {
    protected PieceColor color;
    protected Position position;
    protected ImageIcon icon;

    public Piece(PieceColor color){
        this.color = color;
        this.position = null;
    }

    public Piece(PieceColor color, ImageIcon pieceIcon){
        this.color = color;
        this.position = null;
        this.icon = pieceIcon;
    }

    public void setPosition(Position position){ this.position = position; }
    public Position getPosition(){ return position; }
    public PieceColor getColor(){ return color; }


    public ArrayList<Position> getEmptyNeighbours(){
        ArrayList<Position> emptyNeighbours = new ArrayList<Position>();

        for(Position position : position.getNeighbours()){
            if(position.getPiece() == null)
                continue;
            emptyNeighbours.add(position);
        }

        return emptyNeighbours;
    }

    public ImageIcon getNormalIcon(){ return icon; };
    abstract public ImageIcon getPressedIcon();

    abstract public ImageIcon getRolloverIcon();

    @Override
    public String toString() {
        String out = "Piece(color=" + color + ", position=" + position + ")";
        return out;
    }
}