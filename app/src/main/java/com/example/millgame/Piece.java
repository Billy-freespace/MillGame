package com.example.millgame;

import java.util.ArrayList;
import javax.swing.*;


public class Piece extends JButton {
    protected PieceColor color;
    protected Position position;


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


class WhitePiece extends Piece {
    public static Icon pieceIcon = new ImageIcon("resources/SOMTHING-WHITE.jpg");
    public static  Icon selectedPieceIcon = new ImageIcon("resources/SOMTHING-WHITE.jpg");
    public WhitePiece(){
        super(PieceColor.WHITE);
    }
    public WhitePiece(Position position){
        super(PieceColor.WHITE, position);
    }
}

class BlackPiece extends Piece {

    public static Icon pieceIcon = new ImageIcon("resources/SOMTHING-BLACK.jpg");
    public static  Icon selectedPieceIcon = new ImageIcon("resources/SOMTHING-BLACK.jpg");
    public BlackPiece(){
        super(PieceColor.BLACK);
    }
    public BlackPiece(Position position){
        super(PieceColor.BLACK, position);
    }
}