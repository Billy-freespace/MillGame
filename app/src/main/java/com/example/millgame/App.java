package com.example.millgame;

import com.example.millgame.graphicsAndSounds.Assets;

import java.awt.*;
import java.util.logging.Level;

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
                parser.addArgument("-D", "-debug")
                        .action(Arguments.storeTrue())
                        .help("Enable console logger");
                parser.addArgument("--log-file")
                        .dest("logfile")
                        .setDefault("millgame.log")
                        .help("Output logging file");

                parser.addArgument("--log-level")
                        .dest("loglevel")
                        .choices(Level.FINEST,
                                Level.FINER,
                                Level.FINE,
                                Level.CONFIG,
                                Level.INFO,
                                Level.WARNING,
                                Level.SEVERE)
                        .setDefault(Level.INFO)
                        .help("Logging level");

                parser.addArgument("-v")
                        .action(Arguments.count())
                        .setDefault(0)
                        .type(Integer.class)
                        .help("Verbose mode");

                try{
                    Namespace ns = parser.parseArgs(args);
                    System.out.println(ns.toString());
                } catch (ArgumentParserException error){
                    parser.handleError(error);
                    System.exit(1);
                }

                GameGUI gameGUI = new GameGUI();
                gameGUI.setVisible(true);
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