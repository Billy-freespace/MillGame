package com.example.millgame;

import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.boards.BoardDimension;
import com.example.millgame.exceptions.*;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Color;
import java.util.*;
import java.util.logging.Level;

public abstract class Board implements BoardDimension {
    protected Position origin;
    protected Map<Character, Map<Integer, Position>> positions;
    public final BoardVariant variant;
    protected Map<Color, List<Mill>> mills;

    protected PieceRadar radar;

    protected Map<Color, Integer> pieceCount;

    public Board (BoardVariant variant, List<Color> playerColors) {
        this.variant = variant;
        this.positions = new HashMap<Character, Map<Integer, Position>>();
        this.origin = null;

        mills =  new HashMap<Color, List<Mill>>();
        pieceCount = new HashMap<Color, Integer>();
        for(Color color : playerColors){
            mills.put(color, new ArrayList<Mill>());
            pieceCount.put(color, 0);
        }
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

    public void placePiece(Piece piece, char x, int y)
            throws NotEmptyPosition, InvalidPositionCoordinate {
        Position position = null;
        
        position = this.getPosition(x, y);

        Piece positionPiece = position.getPiece();

        if(positionPiece != null) {
            throw new NotEmptyPosition(x, y);
        }
        position.setPiece(piece);
        piece.setPosition(position);

        int count = pieceCount.get(piece.getColor());
        count += 1;
        pieceCount.put(piece.getColor(), count);

        List<Mill> pieceMills = getMills(piece);
        List<Mill> colorMills = mills.get(piece.getColor());
        colorMills.addAll(pieceMills);
    }

    public void removePiece(char x, int y) throws InvalidPositionCoordinate, EmptyPositionError{
        Position position = getPosition(x, y);

        Piece piece = position.getPiece();
        if(piece == null){
            throw new EmptyPositionError(position);
        }

        // remove all mills where piece belongs
        List<Mill> colorMills = mills.get(piece.getColor());
        ArrayList<Mill> colorMillsCopy = new ArrayList<Mill>(colorMills); // making a copy
        for(Mill mill : colorMillsCopy){
            if(mill.hasPiece(piece)){
                colorMills.remove(mill);
            }
        }

        piece.setPosition(null);
        position.setPiece(null);

        int count = pieceCount.get(piece.getColor());
        count -= 1;
        pieceCount.put(piece.getColor(), count);
    }

    public List<Mill> getMills(Color color){ return mills.get(color); }
    public abstract List<Mill> getMills(Piece piece);
    public BoardVariant getVariant() {
        return variant;
    }

    public Position getPosition(char x, int y) throws InvalidPositionCoordinate {
        if(!positions.containsKey(x)){
            throw new InvalidPositionCoordinate(x, y);
        }

        Map<Integer, Position> inner = positions.get(x);
        if(!inner.containsKey(y)){
            throw new InvalidPositionCoordinate(x, y);
        }

        return inner.get(y);
    }
/*
// REMOVE THIS METHOD - WROTE JUST FOR TESTING PURPOSE
    public void listPositions(){
        TraceLogger.log(Level.INFO, "============================= ListPositions() =============================");
        Iterator<Map.Entry<Integer, Position>> itr;
        for(Character x : positions.keySet()){
            Map<Integer, Position> inner = positions.get(x);
            itr = inner.entrySet().iterator();
            while(itr.hasNext()){
                Map.Entry<Integer, Position> entry = itr.next();
                Position position = entry.getValue();
                int y = entry.getKey();
                TraceLogger.log(Level.INFO, "Position (" + x + ", " + y + "): " + position);
            }
        }
    }
 */

    public int getCount(Color color){ return pieceCount.get(color); }

    // Override this method if fly is not allow in a specific board
    public List<Position> getPossibleMovements(Piece piece) {
        List<Position> possibleMovements = new ArrayList<Position>();
        Position position = piece.getPosition();

        try{
            if(position == null){
                throw new InvalidBoardPiece(piece);
            }

            Color color = piece.getColor();
            int count = getCount(color);

            if(count == 3){
                possibleMovements.addAll(getEmptyPositions());
            } else {
                for(Position neighbour : position.getNeighbours()){
                    if(!neighbour.hasPiece()){ // empty position
                        possibleMovements.add(neighbour);
                    }
                }
            }
        } catch (InvalidBoardPiece error){
            TraceLogger.log(error);
        }


        return possibleMovements;
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
        Map<Integer, Position> inner;

        if(!positions.containsKey(xLabel)){
            inner = new HashMap<Integer, Position>();
            positions.put(xLabel, inner);
        }

        inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }

    public boolean inAnyMill(Piece piece){
        Color color = piece.getColor();

        List<Mill> colorMills = mills.get(color);

        boolean result = false;

        for(Mill mill : colorMills){
            if(mill.hasPiece(piece)){
                result = true;
                break;
            }
        }

        return result;
    }

    public abstract Mill createMill(List<Piece> pieces);

    /*
     * inner classes
     */
    public abstract class Mill {
        private List<Piece> pieces;
        private Color color;

        public Mill(List<Piece> pieces) throws InvalidMill, InvalidMillSize, InvalidMillColor {
            if(pieces.size() != 3){
                throw  new InvalidMillSize(pieces);
            }

            color = pieces.get(0).getColor();

            for(Piece piece : pieces){
                if(piece.getColor() != color){
                    throw new InvalidMillColor(pieces);
                }
            }

            if(!isValid(pieces)){
                throw new InvalidMill(pieces);
            }
            this.pieces = pieces;
        }

        protected abstract boolean isValid(List<Piece> pieces);
        //public void addPiece(Piece piece) { pieces.add(piece); }
        public boolean hasPiece (Piece piece) { return pieces.contains(piece); }

        @Override
        public String toString() {
            String out="";

            out += "Mill(color=" + color + ", Positions=[";
            for(Piece piece : pieces){
                out += piece.getPosition() + ", ";
            }
            out += "])";

            return out;
        }
    }

    /*
     * Inner enumerations
     */

    public enum BoardVariant {
        THREE_MEN_MORRIS,
        SIX_MEN_MORRIS,
        SEVEN_MEN_MORRIS,
        NINE_MEN_MORRIS,
        TWELVE_MEN_MORRIS
    }
}
