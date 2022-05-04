package com.example.millgame.exceptions;

public class InvalidPositionCoordinate extends Exception{
    public InvalidPositionCoordinate(char xLabel, int yLabel){
        super(InvalidPositionCoordinate.getErrorMessage(xLabel, yLabel));
    }

    public static  String getErrorMessage(char xLabel, int yLabel){
        String message =  "No such position in board: Position(x: " + xLabel + ", y: " + Integer.toString(yLabel) + ")";
        return message;
    }
}
