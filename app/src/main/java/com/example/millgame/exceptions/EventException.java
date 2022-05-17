package com.example.millgame.exceptions;


import java.awt.event.ActionEvent;
import java.util.logging.Level;

public class EventException extends RankedException{
    public EventException(ActionEvent event, String message){
        super(EventException.getErrorMessage(event, message));
    }
    public EventException(ActionEvent event, String message, Level rank){
        super(EventException.getErrorMessage(event, message), rank);
    }

    static String getErrorMessage(ActionEvent event, String message){
        return "Event: " + event.toString() + ", Error: " + message;
    }
}
