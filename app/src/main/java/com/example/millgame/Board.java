package com.example.millgame;

import com.example.millgame.MillGame.GameVariant;

import java.util.HashMap;


public abstract class Board {
    private Position origin;
    private GameVariant variant;
    private HashMap mills;

    public Board (GameVariant variant) {
        this.variant = variant;
    }

    public GameVariant getGameVariant() {
        return variant;
    }
    public static int getNumberPieces (GameVariant variant) {
        return 0;
    }
    public void placePiece(Piece piece, char x, char y){}
    public void removePiece(Position position){};
    public Position getPosition(char x, char y){return null;}

}
