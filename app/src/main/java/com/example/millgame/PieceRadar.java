package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.CardinalDirection;
import com.example.millgame.misc.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public  class PieceRadar {
    private Map<CardinalDirection, List<Piece>> pieces;
    private List<Direction> directions;
    private Board board;

    public PieceRadar(List<Direction> directions, Board board){
        pieces = new HashMap<CardinalDirection, List<Piece>>();
        this.directions = directions;
        this.board = board;
    }

    private CardinalDirection getCardinalDirection(Direction direction, int variantX, int variantY) throws RankedException {
        CardinalDirection cardinalDirection;
        switch (direction){
            case VERTICAL:
                if(variantX != 0){
                    throw new RankedException("Invalid variant of X in direction="+ direction);
                }

                cardinalDirection = CardinalDirection.N;
                if(variantY < 0){
                    cardinalDirection = CardinalDirection.S;
                }
                break;
            case HORIZONTAL:
                if(variantY != 0){
                    throw new RankedException("Invalid variant of Y in direction="+ direction);
                }
                cardinalDirection = CardinalDirection.E;
                if(variantX < 0){
                    cardinalDirection = CardinalDirection.W;
                }

            case DIAGONAL:
                if(variantY == 0 || variantX == 0){
                    throw new RankedException("Invalid variant of X and Y in direction="+ direction);
                }
                cardinalDirection = CardinalDirection.SE;
                if(variantX > 0 && variantY < 0){
                    cardinalDirection = CardinalDirection.NW;
                }
            case ANTIDIAGONAL:
                if(variantY*variantX <= 0){
                    throw new RankedException("Invalid variant of (x, y) in direction="+ direction);
                }
                cardinalDirection = CardinalDirection.NE;
                if(variantX < 0 && variantY < 0){
                    cardinalDirection = CardinalDirection.SW;
                }
                break;
            default: // this case never happens, but it's preferred to raise an exception to set cardinalDirection to null
                throw new RankedException("Invalid direction: "+ direction);
        }
        return cardinalDirection;
    }

    public void map(Piece piece) {
        //Map all pieces of the same color around its position

        reset();
        board.unmark();
        for(Direction direction: directions){
            mapDirection(piece, direction);
        }
    }

    public int getCount(CardinalDirection direction){
        return pieces.get(direction).size();
    }

    public List<Piece> getPieces(CardinalDirection direction){
        return pieces.get(direction);
    }

    private void mapDirection(Piece piece, Direction direction){
        /*
         * map all neighbour positions that contain a piece of the same color
         * and their coordinate are L: (x, y) + n*(variantX, variantY), where n is positive number
         * NOTE:
         * x = piece.getPosition().getXLabel(), y = piece.getPosition().getYLabel()
         * L = line equation -> mapLine
         */

        Position position = piece.getPosition();
        Map<CardinalDirection, List<Piece>> mappedPieces = new HashMap<CardinalDirection, List<Piece>>();

        char x = position.getXLabel();
        int y = position.getYLabel();

        int variantX, variantY;
        position.mark = true;
        for(Position neighbour : position.getNeighbours()){
            Piece neighbourPiece = neighbour.getPiece();
            if(neighbourPiece != null && !neighbour.mark &&
                    neighbourPiece.getColor() == piece.getColor()){
                try{
                    variantX = neighbour.getXLabel() - position.getXLabel();
                    variantY = neighbour.getYLabel() - position.getYLabel();

                    CardinalDirection cardinalDirection = getCardinalDirection(direction, variantX, variantY);
                    pieces.get(cardinalDirection).add(neighbourPiece);
                    mapDirection(neighbourPiece, direction);
                } catch (RankedException error){
                    TraceLogger.log(error);
                }
            }
        }
        TraceLogger.log(Level.INFO, position + "\n\n" + this.toString());
    }

    private void reset(){
        for(CardinalDirection direction : CardinalDirection.values()){
            pieces.put(direction, new ArrayList<Piece>());
        }
    }

    @Override
    public String toString() {
        String out="\n";

        for(CardinalDirection direction : CardinalDirection.values()){
            out += direction + " : " + pieces.get(direction).size();
            out += "\nPositions:";
            for(Piece piece : pieces.get(direction)){
                out += piece.getPosition() + ", ";
            }
            out += "\n------------\n";
        }
        return out;
    }
}