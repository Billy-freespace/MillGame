package com.example.millgame;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.boards.BoardDimension;
import com.example.millgame.exceptions.EmptyPositionError;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.exceptions.RankedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public abstract class Board implements BoardDimension {
    protected Position origin;
    protected Map<Character, Map<Integer, Position>> positions;
    public final GameVariant variant;
    //protected HashMap<PieceColor, ArrayList<Mill>> mills;

    public Board (GameVariant variant) {
        this.variant = variant;
        this.positions = new HashMap<Character, Map<Integer, Position>>();
        this.origin = null;
/*
        mills =  new HashMap<PieceColor, ArrayList<Mill>>();
        mills.put(PieceColor.BLACK, new ArrayList<Mill>());
        mills.put(PieceColor.WHITE, new ArrayList<Mill>());
*/
    }

    public ArrayList<Position> getEmptyPositions(){
        ArrayList<Position> emptyPositions = new ArrayList<Position>();
        Position position;

        for(Character x : positions.keySet()){
            Map<Integer, Position> inner = positions.get(x);
            for(Integer y : inner.keySet()){
                position = inner.get(y);

                if(!position.hasPiece()){
                    emptyPositions.add(position);
                }
            }
        }

        return emptyPositions;
    }
    public abstract ArrayList<Position> getPossibleMovements(char xLabel, int yLabel);
    public void placePiece(Piece piece, char xLabel, int yLabel) throws NotEmptyPosition, InvalidPositionCoordinate {
        Position position = null;
        
        position = this.getPosition(xLabel, yLabel);

        Piece positionPiece = position.getPiece();

        if(positionPiece != null) {
            throw new NotEmptyPosition(xLabel, yLabel);
        }
        position.setPiece(piece);
        piece.setPosition(position);
    }

    public void removePiece(char xLabel, int yLabel) throws InvalidPositionCoordinate, EmptyPositionError{
        Position position = getPosition(xLabel, yLabel);

        Piece piece = position.getPiece();
        if(piece == null){
            throw new EmptyPositionError(position);
        }

        piece.setPosition(null);
        position.setPiece(null);
    }


    public void removeMarks(){
        // ITERATE OVER ALL POSITIONS AND SET mark to false
    }

    public ArrayList<Mill> searchMills(){
        // sprint 2
        return null;
    }

    public abstract boolean isValidMill(Mill mill);
    public GameVariant getVariant() {
        return variant;
    }

    public abstract int getNumberPlayerPieces();

    public Position getPosition(char xLabel, int yLabel) throws InvalidPositionCoordinate {
        if(!positions.containsKey(xLabel)){
            throw new InvalidPositionCoordinate(xLabel, yLabel);
        }

        Map<Integer, Position> inner = positions.get(xLabel);
        if(!inner.containsKey(yLabel)){
            throw new InvalidPositionCoordinate(xLabel, yLabel);
        }

        return inner.get(yLabel);
    }

    public int countPositions(){
        int count =0;
        for(Map<Integer, Position> inner : positions.values()){
            count += inner.size();
        }

        return count;
    }

    public void setOrigin(Position origin){ this.origin = origin;}
    public Position getOrigin(){ return origin; }
    public void unmark(){
        for(Character xLabel : positions.keySet()){
            Map<Integer, Position> inner = positions.get(xLabel);
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

        Map<Integer, Position> inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }

    /*
     * Mill inner class
     */
    public abstract class Mill {
        private Set<Piece> pieces;

        public Mill(Set<Piece> pieces) throws RankedException {
            if(pieces.size() > 3){
                throw new RankedException("Invalid number positions to form a mill", Level.WARNING);
            }

            this.pieces = pieces;
        }

        public abstract boolean isValid(Set<Piece> pieces);
        public void addPosition(Piece piece) { pieces.add(piece); }
        public boolean inMill (Piece piece) { return pieces.contains(piece); }
    }
}
