package com.example.millgame.exceptions;

import com.example.millgame.Position;
import java.util.logging.Level;

public class EmptyPositionError extends RankedException{

    public EmptyPositionError(char xLabel, int yLabel){
        super(EmptyPositionError.getErrorMessage(xLabel, yLabel));
    }
    public EmptyPositionError(Position position){
        super(EmptyPositionError.getErrorMessage(position));
    }

    public EmptyPositionError(Position position, Level level){
        super(EmptyPositionError.getErrorMessage(position), level);
    }

    public static String getErrorMessage(Position position){
        String error = position + " has not piece on it";
        return error;
    }

    public static String getErrorMessage(char xLabel, int yLabel){
        Position position = new Position(xLabel, yLabel);
        String error = position + " has not piece on it";
        return error;
    }
}
