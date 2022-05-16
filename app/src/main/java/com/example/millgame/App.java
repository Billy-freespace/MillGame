package com.example.millgame;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;
import com.example.millgame.logging.TraceMode;
import com.example.millgame.misc.CmdParser;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class App {

    // debug options
    protected static String traceLoggerName;
    protected static String logfile;
    protected static TraceMode traceMode;
    protected static boolean debug;

    public static void main(String[] args){
        Runnable runner = new Runnable(){
            @Override
            public void run(){
                ArgumentParser parser = CmdParser.debugArgumentParser();

                try{
                    parseCmdArguments(parser, args);

                    GameGUI gameGUI = new GameGUI();

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

    public static Namespace parseCmdArguments(ArgumentParser parser, String[] args) throws ArgumentParserException, IOException, RankedException {
        Namespace ns = parser.parseArgs(args);

        logfile = ns.getString("logfile");
        debug = ns.getBoolean("debug");

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

        traceLoggerName = ns.getString("traceLoggerName");

        TraceLogger traceLogger = TraceLogger.initTraceLogger(traceLoggerName, logfile, debug);
        traceLogger.setTraceMode(traceMode);

        TraceMessage message = new TraceMessage(
                Level.INFO,
                "logfile: " + logfile +
                        "\ndebug: " + debug +
                        "\ntraceMode: " + traceMode +
                        "\ntraceLoggerName: " + traceLoggerName +
                        "\nverboseLevel: " + verboseLevel);

        TraceLogger.log(message);
        return ns;
    }
}