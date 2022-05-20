package com.example.millgame.exceptions;

import java.util.logging.Level;

public class NullTraceLoggerInstance extends RankedException{
    public NullTraceLoggerInstance(){
        super("TraceLogger instance was not initialized", Level.WARNING);
    }
}
