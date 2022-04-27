package com.example.millgame;

import java.util.ArrayList;

class Position {
    private char x;
    private char y;
    private Piece piece;
    private ArrayList<Position> neighbours;
    boolean mark = false;

    Position(char x, char y){}
    public void setPiece(Piece piece){}
    public Piece getPiece(){return null;}
    public ArrayList<Position> getNeighbours(){return null;}
}

public abstract class Board {
    public GameVariant getGameVariant(){return null;}
    public static int getNumberPieces(GameVariant variant){return 0;}
    public void placePiece(Piece piece, char x, char y){}
    public void removePiece(Position position){};
    public Position getPosition(char x, char y){return null;}
}
