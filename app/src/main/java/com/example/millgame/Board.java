package com.example.millgame;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.logging.GameLogger;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Board{
    protected Position origin;
    protected HashMap<Character, HashMap<Integer, Position>> positions;
    public final GameVariant variant;
    protected HashMap<PieceColor, ArrayList<Mill>> mills;
    protected GameLogger logger;

    public Board (GameVariant variant) {
        this.variant = variant;

        mills =  new HashMap<PieceColor, ArrayList<Mill>>();
        mills.put(PieceColor.BLACK, new ArrayList<Mill>());
        mills.put(PieceColor.WHITE, new ArrayList<Mill>());

        positions = new HashMap<Character, HashMap<Integer, Position>>();
    }

    public ArrayList<Position> getEmptyPositions(){
        return null;
    }
    public void placePiece(Piece piece, char xLabel, int yLabel){
        Position position = this.getPosition(xLabel, yLabel);
        Piece positionPiece = position.getPiece();

        if(positionPiece != null) {
            /*
             * RAISE AN EXCEPTION, BECAUSE POSITION (xLabel, yLabel) IS NOT EMPTY
             */
        }
        position.setPiece(piece);
        piece.setPosition(position);
    }

    public void removePiece(char xLabel, int yLabel) {
        Position position = this.getPosition(xLabel, yLabel);
        this.removePiece(position);
    }

    public void removePiece(Position position){
        Piece piece = position.getPiece();
        piece.setPosition(null);
        position.setPiece(null);
    };

    public void removeMarks(){
        // ITERATE OVER ALL POSITIONS AND SET mark to false
    }

    public ArrayList<Mill> searchMills(){
        // IMPLEMENT ME (sprint 2)
        return null;
    }

    public abstract boolean isValidMill(Mill mill);
    public GameVariant getGameVariant() {
        return variant;
    }
    public static int getNumberPieces (GameVariant variant) {
        int npieces;

        switch (variant){
            case NINE_MEN_MORRIS:
                npieces = 9;
                break;

            default:
                npieces = -1;
        }
        return npieces;
    }

    public void setPosition(char xLabel, int yLabel, Position position){
        HashMap<Integer, Position> inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }

    public Position getPosition(char xLabel, int yLabel) {
        HashMap<Integer, Position> inner = positions.get(xLabel);
        return inner.get(yLabel);
    }

    public void setLogger(GameLogger logger){ this.logger = logger; }
    public void setOrigin(Position origin){ this.origin = origin;}
}
