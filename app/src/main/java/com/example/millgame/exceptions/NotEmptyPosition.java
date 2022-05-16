package com.example.millgame.exceptions;

import com.example.millgame.Position;

import java.util.logging.Level;

public class NotEmptyPosition extends RankedException {

    public NotEmptyPosition(char xLabel, int yLabel) {
        super(NotEmptyPosition.getErrorMessage(xLabel, yLabel));
    
    }

    public NotEmptyPosition(Position position){
        super(NotEmptyPosition.getErrorMessage(position.getXLabel(), position.getYLabel()));
    }

    public NotEmptyPosition(char xLabel, int yLabel, Level rank) {
        super(NotEmptyPosition.getErrorMessage(xLabel, yLabel), rank);
    }

    public static  String getErrorMessage(char xLabel, int yLabel){
        String message =  "Position(x: " + xLabel + ", y: " + Integer.toString(yLabel) + ")" + " is not empty";
        return message;
    }
}
