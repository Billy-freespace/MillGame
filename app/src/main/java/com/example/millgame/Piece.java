package com.example.millgame;

import java.util.List;

enum PieceColor {
    WHITE,
    BLACK
}

public class Piece {
    private PieceColor color;
    private Position position;

    public Piece(PieceColor color){}; // piece has no defined position
    public Piece(PieceColor color, Position position){};
    public void move2position(char x, char y){};
    public List<Position> getEmptyNeibours(){};
    public void setPosition(Position position){this.position = position;}
    public Position getPosition(){return position;}
}
