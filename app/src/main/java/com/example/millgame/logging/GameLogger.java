package com.example.millgame.logging;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class GameLogger extends Logger {
    static public final Path DEFAULT_LOGFILE = Paths.get("millgame.log");
    static public final Level DEFAULT_LOGLEVEL = Level.INFO;
    //static public final For DEFAULT_LOGFMT = 1;

    protected GameLogger(String name, Path logfile, Level loglevel) {
        super(name, null);

        try {
            FileHandler fh = new FileHandler(logfile.toString(), true);
            fh.setLevel(loglevel);
            fh.setFormatter(new SimpleFormatter());
            addHandler(fh);
        }
        catch (IOException error){
            System.out.println(error.getMessage());
        }
    }

    public static GameLogger getLogger(String name){
        GameLogger logger = new GameLogger(name, DEFAULT_LOGFILE, DEFAULT_LOGLEVEL);
        return logger;
    }

    public static GameLogger getLogger(String name, String logfile){
        Path logfilePath = Paths.get(logfile);
        GameLogger logger = new GameLogger(name, logfilePath, DEFAULT_LOGLEVEL);
        return logger;
    }
}
