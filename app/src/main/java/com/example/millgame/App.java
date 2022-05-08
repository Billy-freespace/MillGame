package com.example.millgame;

import java.awt.*;

import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMode;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class App {
    public static void main(String[] args){
        Runnable runner = new Runnable(){
            public void run(){
                ArgumentParser parser = ArgumentParsers.newFor("MillGame").build()
                        .defaultHelp(true)
                        .description("Mill Game - Debug options");
                parser.addArgument("-D", "--debug")
                        .action(Arguments.storeTrue())
                        .help("Enable console logger");
                parser.addArgument("--log-file")
                        .dest("logfile")
                        .setDefault("millgame.log")
                        .help("Output logging file (location: app/millgame.log)");
                parser.addArgument("-n", "--logger-name")
                        .dest("traceLoggerName")
                        .setDefault("millgame")
                        .help("Trace logger name");

                String verboseHelp = "Verbose mode\n\n" +
                        "Verbose Level (Number of 'v's):\n" +
                        "* 1: curious mode - traced logging levels: WARNING, SEVERE\n" +
                        "* 2: developer mode - traced logging levels: CONFIG, INFO, WARNING, SEVERE\n" +
                        "* 3: paranoid mode - traced logging levels: ALL";

                parser.addArgument("-v")
                        .action(Arguments.count())
                        .setDefault(0)
                        .dest("verbose")
                        .type(Integer.class)
                        .help(verboseHelp);

                try{
                    Namespace ns = parser.parseArgs(args);
                    System.out.println(ns.toString());

                    String logfile = ns.getString("logfile");
                    boolean debug = ns.getBoolean("debug");

                    TraceMode traceMode = null;
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
                    }

                    System.out.println("TraceMode: " + traceMode);

                    String name = ns.getString("traceLoggerName");
                    TraceLogger traceLogger = TraceLogger.getTraceLogger(name, logfile, debug);
                    //System.out.println("(TraceLogger init) TraceMode: " +traceLogger.getTraceMode());
                    traceLogger.setTraceMode(traceMode);
                    //System.out.println("(TraceLogger after) TraceMode: " +traceLogger.getTraceMode());

                    GameGUI gameGUI = new GameGUI();
                    gameGUI.setTraceLogger(traceLogger);
                    gameGUI.setVisible(true);

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

/*
public class App implements Runnable {
    private Thread threadGUI;

    public static void main(String[] args){
        System.out.println("HELLO WORLD!");
        // COMMAND LINE PARSER - TO ENABLE LOG TO CONSOLE
        System.out.println("Arguments: " + String.join(" ", args));
        new App().start();
    }

    @Override
    public void run() {
        Assets.init();
        GameGUI gameGUI = new GameGUI();
        gameGUI.setVisible(true);
    }

    private void start() {
        threadGUI = new Thread(this);
        threadGUI.start();
    }

    public void stop() {
        try {
            threadGUI.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
*/