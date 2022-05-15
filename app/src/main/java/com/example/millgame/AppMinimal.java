package com.example.millgame;

import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMode;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.awt.*;

public class AppMinimal {
    public static void main(String[] args){
        Runnable runner = new Runnable(){
            @Override
            public void run(){
                ArgumentParser parser = App.getCmdParser();

                try {
                    Namespace ns = parser.parseArgs(args);

                    String logfile = ns.getString("logfile");
                    boolean debug = ns.getBoolean("debug");

                    TraceMode traceMode;
                    int verboseLevel = ns.getInt("verbose");
                    switch (verboseLevel){
                        case 2:
                            traceMode = TraceMode.DEVELOPER;
                            break;
                        case 3:
                            traceMode = TraceMode.PARANOID;
                            break;
                        default:
                            traceMode = TraceMode.CURIOUS;
                            break;
                    }

                    String name = ns.getString("traceLoggerName");
                    TraceLogger traceLogger = TraceLogger.initTraceLogger(name, logfile, debug);
                    traceLogger.setTraceMode(traceMode);

                    MillGame.GameVariant variant = MillGame.GameVariant.NINE_MEN_MORRIS;
                    MillGame.GameMode mode = MillGame.GameMode.HUMAN_HUMAN;

                    GameGUIMinimal gameGUI = new GameGUIMinimal(variant, mode);

                } catch (ArgumentParserException error){
                    parser.handleError(error);
                    System.exit(1);
                }catch (Exception error){
                    System.err.println(error.getMessage());
                    System.exit(1);
                }
            }
        };

        EventQueue.invokeLater(runner);
    }
}
