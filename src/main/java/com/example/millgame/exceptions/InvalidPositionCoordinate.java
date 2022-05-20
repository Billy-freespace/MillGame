package com.example.millgame.exceptions;

import java.util.logging.Level;

public class InvalidPositionCoordinate extends RankedException{
    public InvalidPositionCoordinate(char xLabel, int yLabel){
        super(InvalidPositionCoordinate.getErrorMessage(xLabel, yLabel));
    }

    public InvalidPositionCoordinate(char xLabel, int yLabel, Level rank){
        super(InvalidPositionCoordinate.getErrorMessage(xLabel, yLabel), rank);
    }

    public static  String getErrorMessage(char xLabel, int yLabel){
        String message =  "No such position in board: Position(x: " + xLabel + ", y: " + Integer.toString(yLabel) + ")";
        return message;
    }
}
