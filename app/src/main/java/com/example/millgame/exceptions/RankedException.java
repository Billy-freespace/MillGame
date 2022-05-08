package com.example.millgame.exceptions;

import java.util.logging.Level;

public class RankedException extends Exception{
    public Level rank;

    public RankedException(Exception exception, Level rank){
        super(exception);
        this.rank = rank;
    }
    public RankedException(String message){
        super(message);
        rank = Level.WARNING; // default exception rank
    }
    public RankedException(String message, Level rank){
        super(message);
        this.rank = rank;
    }
}
