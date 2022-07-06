package com.example.millgame;

import com.example.millgame.misc.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class PieceRadar {
    private Map<Direction, List<Piece>> pieces;
    private final List<Direction> directions;
    private final Board board;

    public PieceRadar(List<Direction> directions, Board board){
        pieces = new HashMap<Direction, List<Piece>>();
        this.directions = directions;
        this.board = board;
    }

    private boolean validateDirection(Direction direction, int variantX, int variantY){
        boolean result = false;
        switch (direction){
            case VERTICAL:
                if(variantX == 0 && variantY != 0){
                    result = true;
                }
                break;

            case HORIZONTAL:
                if(variantY == 0 && variantX != 0){
                    result = true;
                }
                break;

            case DIAGONAL:
                if(variantX*variantY < 0){
                    result = true;
                }
                break;

            case ANTIDIAGONAL:
                if(variantX*variantY > 0){
                    result = true;
                }
                break;
        }

        return result;
    }

    public void map(Piece piece) {
        //Map all pieces of the same color around its position

        reset();
        board.unmark();
        for(Direction direction: directions){
            mapDirection(piece, direction);
        }
    }

    public int getCount(Direction direction){
        return pieces.get(direction).size();
    }

    public List<Piece> getPieces(Direction direction){
        return pieces.get(direction);
    }

    private void mapDirection(Piece piece, Direction direction){
        /*
         * map all neighbour positions that contain a piece of the same color
         * in the same direction (VERTICAL, HORIZONTAL, DIAGONAL, ANTIDIAGONAL)
         * NOTE: VERTICAL = NORTH + SOUTH, ...
         */

        Position position = piece.getPosition();

        char x = position.getXLabel();
        int y = position.getYLabel();

        int variantX, variantY;
        position.mark = true;
        for(Position neighbour : position.getNeighbours()){
            Piece neighbourPiece = neighbour.getPiece();

            variantX = (int) neighbour.getXLabel() - (int) position.getXLabel();
            variantY = neighbour.getYLabel() - position.getYLabel();

            if(neighbourPiece != null && !neighbour.mark &&
                    neighbourPiece.getColor() == piece.getColor() &&
                    validateDirection(direction, variantX, variantY)){

                    pieces.get(direction).add(neighbourPiece);
                    mapDirection(neighbourPiece, direction);
            }
        }
        TraceLogger.log(Level.INFO, position + "\n\n" + this.toString());
    }

    private void reset(){
        for(Direction direction : Direction.values()){
            pieces.put(direction, new ArrayList<Piece>());
        }
    }

    @Override
    public String toString() {
        String out="\n";

        for(Direction direction : directions){
            out += direction + " : ";
            for(Piece piece : pieces.get(direction)){
                out += piece.getPosition() + ", ";
            }
            out += "\n------------\n";
        }
        return out;
    }
}