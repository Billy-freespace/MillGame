package com.example.millgame.logging;

import java.util.logging.Level;

public class TraceMessage {
    public Level rank;
    private Class cls;
    private String message;

    public TraceMessage(String message){}
    public TraceMessage(String message, Class cls){}
    public TraceMessage(String message, Level level){}

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
