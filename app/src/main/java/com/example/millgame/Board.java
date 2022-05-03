package com.example.millgame;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Piece.PieceColor;
import com.example.millgame.logging.GameLogger;

import java.util.HashMap;


public abstract class Board{
    protected Position origin;
    protected HashMap<Character, HashMap<Integer, Position>> positions;
    public final GameVariant variant;
    protected HashMap<PieceColor, Mill> mills;
    protected GameLogger logger;

    public Board (GameVariant variant) {
        this.variant = variant;
    }

    public GameVariant getGameVariant() {
        return variant;
    }
    public static int getNumberPieces (GameVariant variant) {
        return 0;
    }
    public void placePiece(Piece piece, char xLabel, int yLabel){}
    public void removePiece(Position position){};

    public abstract boolean isValidMill(Mill mill);

    public void setLogger(GameLogger logger){}
    public void setOrigin(Position origin){ this.origin = origin;}
    public void setPosition(char xLabel, int yLabel, Position position){
        HashMap<Integer, Position> inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }

    public Position getPosition(char xLabel, int yLabel) {
        HashMap<Integer, Position> inner = positions.get(xLabel);
        return inner.get(yLabel);
    }
}
