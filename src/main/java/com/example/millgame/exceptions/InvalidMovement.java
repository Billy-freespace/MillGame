package com.example.millgame.exceptions;

import com.example.millgame.Position;

import java.util.logging.Level;

public class InvalidMovement extends RankedException {
    public Position from;
    public Position to;

    public InvalidMovement(Position from, Position to) {
        super(InvalidMovement.getErrorMessage(from, to));
        this.from = from;
        this.to = to;
    }

    public InvalidMovement(Position from, Position to, Level level) {
        super(InvalidMovement.getErrorMessage(from, to), level);
        this.from = from;
        this.to = to;
    }

    public static String getErrorMessage(Position from, Position to) {
        return "Invalid movement from: " + from.toString() + ", to: " + to.toString()
                + ". They're not neighbour positions.";
    }

}
