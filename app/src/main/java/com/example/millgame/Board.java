package com.example.millgame;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.pieces.PieceColor;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Board implements BoardDimension {
    protected Position origin;
    protected HashMap<Character, HashMap<Integer, Position>> positions;
    public final GameVariant variant;
    protected HashMap<PieceColor, ArrayList<Mill>> mills;

    public Board (GameVariant variant) {
        this.variant = variant;
        this.positions = new HashMap<Character, HashMap<Integer, Position>>();
        this.origin = null;

        mills =  new HashMap<PieceColor, ArrayList<Mill>>();
        mills.put(PieceColor.BLACK, new ArrayList<Mill>());
        mills.put(PieceColor.WHITE, new ArrayList<Mill>());

    }

    public ArrayList<Position> getEmptyPositions(){
        return null;
    }
    public abstract ArrayList<Position> getPossibleMovements(char xLabel, int yLabel);
    public void placePiece(Piece piece, char xLabel, int yLabel) throws InvalidPositionCoordinate{
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

    public void removePiece(char xLabel, int yLabel) throws InvalidPositionCoordinate{
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
    public GameVariant getVariant() {
        return variant;
    }

    public abstract int getNumberPieces();
    public static int getNumberPieces (GameVariant variant) {
        int npieces = -1;

        switch (variant){
            case NINE_MEN_MORRIS:
            case TWELVE_MEN_MORRIS:
                npieces = 9;
                break;

            default:
                npieces = -1;
        }
        return npieces;
    }

    public Position getPosition(char xLabel, int yLabel) throws InvalidPositionCoordinate {
        if(!positions.containsKey(xLabel)){
            throw new InvalidPositionCoordinate(xLabel, yLabel);
        }

        HashMap<Integer, Position> inner = positions.get(xLabel);
        if(!inner.containsKey(yLabel)){
            throw new InvalidPositionCoordinate(xLabel, yLabel);
        }

        return inner.get(yLabel);
    }

    public int countPositions(){
        int count =0;
        for(HashMap<Integer, Position> inner : positions.values()){
            count += inner.size();
        }

        return count;
    }

    public void setOrigin(Position origin){ this.origin = origin;}
    public Position getOrigin(){ return origin; }
    public void unmark(){
        for(Character xLabel : positions.keySet()){
            HashMap<Integer, Position> inner = positions.get(xLabel);
            for(Integer yLabel : inner.keySet()){
                Position position = inner.get(yLabel);
                position.mark = false;
            }
        }
    } // unmark all positions of board


    public void addPosition(Position position){
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        if(!positions.containsKey(xLabel)){
            positions.put(xLabel, new HashMap<Integer, Position>());
        }

        HashMap<Integer, Position> inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }
}
