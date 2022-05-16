package com.example.millgame;

import com.example.millgame.misc.CardinalDirection;

import java.util.ArrayList;
import java.util.HashMap;

public class PieceRadar {
    private HashMap<CardinalDirection, ArrayList<Position>> positions;
    private Board board;
    PieceRadar(Board board){
        this.board = board;
        positions = new HashMap<CardinalDirection, ArrayList<Position>>();
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
        return positions.get(direction).size();
    }

    public ArrayList<Position> getPositions(CardinalDirection direction){
        return positions.get(direction);
    }

    private ArrayList<Position> mapLine(Piece piece, int variantX, int variantY){
        /*
         * map all neighbour positions that contain a piece of the same color
         * and their coordinate are L: (x, y) + n*(variantX, variantY), where n is positive number
         * NOTE:
         * x = piece.getPosition().getXLabel(), y = piece.getPosition().getYLabel()
         * L = line equation -> mapLine
         */

        Position position = piece.getPosition();
        ArrayList<Position> mapPositions = new ArrayList<Position>();

        char x = position.getXLabel();
        int y = position.getYLabel();

        for(Position neighbour : position.getNeighbours()){
            Piece neighbourPiece = neighbour.getPiece();
            if(neighbourPiece != null && neighbourPiece.getColor() == piece.getColor() &&
                    neighbour.getXLabel() == x + variantX && neighbour.getYLabel() == y + variantY){
                ArrayList<Position> neighbourMapPositions = mapLine(neighbourPiece, variantX, variantY);
                mapPositions.addAll(neighbourMapPositions);
            }
        }
        return mapPositions;
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

        ArrayList<Position> mapPositions = mapLine(piece, variantX, variantY);
        positions.put(direction, mapPositions);
    }

    private void reset(){
        for(CardinalDirection direction : CardinalDirection.values()){
            positions.put(direction, new ArrayList<Position>());
        }
    }
}
