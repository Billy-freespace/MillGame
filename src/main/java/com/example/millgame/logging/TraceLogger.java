package com.example.millgame.logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

import com.example.millgame.exceptions.NullTraceLoggerInstance;
import com.example.millgame.exceptions.RankedException;

public class TraceLogger {
  private static Logger logger;
  private static ArrayList<Handler> handlers;

  private static TraceMode traceMode = null;
  private static TraceMode DEFAULT_TRACE_MODE = TraceMode.DEVELOPER;

  private static TraceLogger instance = null;

  public TraceMode getTraceMode(){ return traceMode; }

  private TraceLogger(String name, String logfile, boolean debugMode) throws IOException, RankedException {
    logger = Logger.getLogger(name);
    FileHandler fh = new FileHandler(logfile);
    fh.setFormatter(new SimpleFormatter());

    handlers = new ArrayList<Handler>();
    handlers.add(fh);
    if(debugMode){
      ConsoleHandler ch = new ConsoleHandler();
      ch.setFormatter(new SimpleFormatter());
      handlers.add(ch);
    }
    setTraceMode(DEFAULT_TRACE_MODE);

    for(Handler handler : handlers){
      logger.addHandler(handler);
    }
  }

  public static TraceLogger initTraceLogger(String name, String logfile, boolean debugMode) throws IOException, RankedException{
    if(instance == null){
      instance = new TraceLogger(name, logfile, debugMode);
    }

    return instance;
  }

  public static void log(Level level, String message){
    TraceMessage traceMessage = new TraceMessage(level, message);
    log(traceMessage);
  }

  public static void log(Level level, String message, Class cls){
    TraceMessage traceMessage = new TraceMessage(level, message, cls);
    log(traceMessage);
  }

  public static void log(TraceMessage message) {
    try{
      checkTraceLoggerInstance();
      logger.log(message.rank, message.toString());
    } catch (NullTraceLoggerInstance error){
      if (traceMode == TraceMode.PARANOID){
        System.err.println(error); // no logger was initialized
      }
    }
  }

  public static void log(RankedException exception){
    try{
      checkTraceLoggerInstance();

      switch (traceMode) {
        case CURIOUS:
        case DEVELOPER:
          String out = exception.getMessage();
          logger.log(exception.rank, out);
          break;

        case PARANOID:
          System.err.println("Trace of " + exception.getClass().getName() + "exception:");
          exception.printStackTrace();
          break;
      }
    } catch (NullTraceLoggerInstance error){
      if (traceMode == TraceMode.PARANOID){
        System.err.println(error); // no logger was initialized
      }
    }
  }

  public static void log(RankedException exception, Class cls){
    // Exception generate in cls class
    try{
      checkTraceLoggerInstance();
      switch (traceMode) {
        case CURIOUS: case DEVELOPER:
          String out = exception.getMessage();
          out = "(Class " + cls.getName() + ") " + out;
          logger.log(exception.rank, out);
          break;

        case PARANOID:
          System.err.println("(Class " + cls.getName() + ") Trace of " +
              exception.getClass().getName() + "exception:");
          exception.printStackTrace();
          break;
      }
    } catch (NullTraceLoggerInstance error){
      if (traceMode == TraceMode.PARANOID){
        System.err.println(error); // no logger was initialized
      }
    }
  }

  public void setTraceMode(TraceMode mode) throws RankedException{
    if(mode == null){
      throw new RankedException("Trying to set traceMode atribute to null", Level.WARNING);
    }
    traceMode = mode;
    Level level = getLoglevel(mode);
    for(Handler handler : handlers){
      handler.setLevel(level);
    }
  }

  private static void checkTraceLoggerInstance() throws NullTraceLoggerInstance {
    if(instance == null){
      throw new NullTraceLoggerInstance();
    }
  }

  private static Level getLoglevel(TraceMode mode){
    Level level = null;
    switch (mode){
      case CURIOUS:
        level = Level.WARNING;

      case DEVELOPER:
        level = Level.CONFIG;

      case PARANOID:
        level = Level.FINEST;
    }
    return level;
  }
}
