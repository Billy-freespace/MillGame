package com.example.millgame.misc;

import com.example.millgame.MillGame;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentGroup;
import net.sourceforge.argparse4j.inf.ArgumentParser;

public class CmdParser {
    public static ArgumentParser debugArgumentParser(){
        ArgumentParser parser = ArgumentParsers.newFor("MillGame").build()
                .defaultHelp(true)
                .description("Mill Game - Command Line Options");

        ArgumentGroup debug = parser.addArgumentGroup("Debug Options");
        debug.addArgument("-D", "--debug")
                .action(Arguments.storeTrue())
                .help("Enable console logger");
        debug.addArgument("--log-file")
                .dest("logfile")
                .setDefault("millgame.log")
                .help("Output logging file (default location: app/millgame.log)");
        debug.addArgument("-n", "--logger-name")
                .dest("traceLoggerName")
                .setDefault("millgame")
                .help("Trace logger name");

        String verboseHelp = "Verbose mode\n\n" +
                "Verbose Level (Number of 'v's):\n" +
                "* 1: curious mode - traced logging levels: WARNING, SEVERE\n" +
                "* 2: developer mode - traced logging levels: CONFIG, INFO, WARNING, SEVERE\n" +
                "* 3: paranoid mode - traced logging levels: ALL";

        debug.addArgument("-v")
                .action(Arguments.count())
                .setDefault(0)
                .dest("verbose")
                .type(Integer.class)
                .help(verboseHelp);

        return parser;
    }

    public static ArgumentParser minimalAppArgumentParser(){
        ArgumentParser parser = debugArgumentParser();
        ArgumentGroup game = parser.addArgumentGroup("Game Configuration Options");
        game.addArgument("--variant")
                .choices(MillGame.GameVariant.values()).setDefault(MillGame.GameVariant.NINE_MEN_MORRIS)
                .type(MillGame.GameVariant.class)
                .help("Game variant");

        game.addArgument("--mode")
                .choices(MillGame.GameMode.values()).setDefault(MillGame.GameMode.HUMAN_HUMAN)
                .type(MillGame.GameMode.class)
                .help("Game mode");

        /*
        game.addArgument("--robot-level")
                .choices(MillGame.GameMode.values()).setDefault(MillGame.GameMode.HUMAN_HUMAN)
                .type(MillGame.GameMode.class)
                .help("Game mode");

         */

        return  parser;
    }
}
