package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;
import com.example.millgame.logging.TraceMode;
import com.example.millgame.misc.CmdParser;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;

public class AppMinimal extends App {
    // game options
    protected static MillGame.GameVariant variant;
    protected static MillGame.GameMode mode;

    // debug options
    /*
    * THESE ATTRIBUTES WERE DEFINED BY App class
    protected static String traceLoggerName;
    protected static String logfile;
    protected static TraceMode traceMode;
    protected static boolean debug;
    */
    public static void main(String[] args){
        Runnable runner = new Runnable(){
            @Override
            public void run(){
                ArgumentParser parser = CmdParser.minimalAppArgumentParser();

                try {
                    parseCmdArguments(parser, args);

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

    public static Namespace parseCmdArguments(ArgumentParser parser, String[] args) throws ArgumentParserException, IOException, RankedException {
        Namespace ns = App.parseCmdArguments(parser, args);
        variant = (MillGame.GameVariant) ns.get("variant");
        mode = (MillGame.GameMode) ns.get("mode");

        TraceMessage message = new TraceMessage(
                Level.INFO,
                "variant: " + variant +
                        "game mode: " + mode);

        TraceLogger.log(message);

        return ns;
    }
}
