package com.example.millgame;

import com.example.millgame.misc.CardinalDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceRadar {
    private Map<CardinalDirection, List<Piece>> pieces;
    private Board board;
    public PieceRadar(Board board){
        this.board = board;
        pieces = new HashMap<CardinalDirection, List<Piece>>();
    }

    public void map(Piece piece){
        /*
         * Map all pieces of the same color around its position
         */
        reset();

        for(CardinalDirection direction: CardinalDirection.values()){
            mapDirection(piece, direction);
        }
    }

    public int getCount(CardinalDirection direction){
        return pieces.get(direction).size();
    }

    public List<Piece> getPieces(CardinalDirection direction){
        return pieces.get(direction);
    }

    private List<Piece> mapLine(Piece piece, int variantX, int variantY){
        /*
         * map all neighbour positions that contain a piece of the same color
         * and their coordinate are L: (x, y) + n*(variantX, variantY), where n is positive number
         * NOTE:
         * x = piece.getPosition().getXLabel(), y = piece.getPosition().getYLabel()
         * L = line equation -> mapLine
         */

        Position position = piece.getPosition();
        ArrayList<Piece> mappedPieces = new ArrayList<Piece>();

        char x = position.getXLabel();
        int y = position.getYLabel();

        for(Position neighbour : position.getNeighbours()){
            Piece neighbourPiece = neighbour.getPiece();
            if(neighbourPiece != null && neighbourPiece.getColor() == piece.getColor() &&
                    neighbour.getXLabel() == x + variantX && neighbour.getYLabel() == y + variantY){
                List<Piece> neighbourMappedPieces = mapLine(neighbourPiece, variantX, variantY);
                mappedPieces.addAll(neighbourMappedPieces);
            }
        }
        return mappedPieces;
    }

    private void mapDirection(Piece piece, CardinalDirection direction){
        int variantX, variantY;

        switch (direction){
            case N:
                variantX = 0;
                variantY = 1;
                break;
            case S:
                variantX = 0;
                variantY = -1;
            case E:
                variantX = 1;
                variantY = 0;
            case W:
                variantX = -1;
                variantY = 0;
            case NE:
                variantX = 1;
                variantY = 1;
            case NW:
                variantX = -1;
                variantY = 1;
            case SE:
                variantX = 1;
                variantY = -1;
            case SW:
                variantX = -1;
                variantY = -1;
            default:
                variantX = 0;
                variantY = 0;
        }

        List<Piece> mappedPieces = mapLine(piece, variantX, variantY);
        pieces.put(direction, mappedPieces);
    }

    private void reset(){
        for(CardinalDirection direction : CardinalDirection.values()){
            pieces.put(direction, new ArrayList<Piece>());
        }
    }
}
