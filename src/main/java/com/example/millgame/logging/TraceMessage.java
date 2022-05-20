package com.example.millgame.logging;

import java.util.logging.Level;

public class TraceMessage {
    public Level rank;
    private Class cls;
    private String message;

    public static final Level DEFAULT_RANK = Level.WARNING;

    public TraceMessage(String message){
        this.cls = null;
        this.message = message;
        this.rank = DEFAULT_RANK;
    }
    public TraceMessage(String message, Class cls){
        this.cls = cls;
        this.message = message;
        rank = DEFAULT_RANK;
    }
    public TraceMessage(Level level, String message){
        cls = null;
        this.message = message;
        rank = level;
    }
    public TraceMessage(Level level, String message, Class cls){
        this.cls = cls;
        this.message = message;
        this.rank = level;
    }

    public void setClass(Class cls){ this.cls = cls; }

    @Override
    public String toString() {
        String out = message;
        if(cls != null){
            out = "(Class: " + cls.getName() + ") " + out;
        }
        return out;
    }
}
