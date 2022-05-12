package com.example.millgame.exceptions;

import java.util.logging.Level;

public class NoEmptyPosition extends RankedException {

    public NoEmptyPosition(char xLabel, int yLabel) {
        super(NoEmptyPosition.getErrorMessage(xLabel, yLabel));
    
    }

    public NoEmptyPosition(char xLabel, int yLabel, Level rank) {
        super(NoEmptyPosition.getErrorMessage(xLabel, yLabel), rank);
    }
    
    public static  String getErrorMessage(char xLabel, int yLabel){
        String message =  "Position in board: Position(x: " + xLabel + ", y: " + Integer.toString(yLabel) + ")" + "is full";
        return message;
    }
}
