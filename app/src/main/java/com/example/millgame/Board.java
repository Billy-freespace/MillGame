package com.example.millgame;

import java.util.ArrayList;

class Position {
    public void setPiece(Piece piece){}
    public Piece getPiece(){}
    public ArrayList<Position> getNeighbours(){}
}

public abstract class Board {
    public GameVariant getGameVariant(){}
    public static int getNumberPieces(GameVariant variant){return 0;}
    public void placePiece(Piece piece, char x, char y){}
    public void removePiece(Position position){};
    public Position getPosition(char x, char y){return null;}
}
