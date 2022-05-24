package com.example.millgame;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.CmdParser;
import com.example.millgame.misc.Color;
import com.example.millgame.players.RobotLevel;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


import java.awt.*;
import java.io.IOException;
import java.util.List;

public class AppMinimal extends App {
    // game options
    protected static MillGame.GameVariant variant;
    protected static MillGame.GameMode mode;

    protected static boolean randomTurn;
    protected static RobotLevel robotLevel;

    protected static int turnTime;

    protected static List<Color> pieceColor;

    private static java.awt.Color boardBackgroundColor;



    public static void main(String[] args){
        Runnable runner = new Runnable(){
            @Override
            public void run(){
                ArgumentParser parser = CmdParser.appMInimalArgumentParser();

                try {
                    parseCmdArguments(parser, args);

                    // Building the game with supplied custom options
                    MillGameBuilder gameBuilder = new MillGameBuilder(variant)
                            .reset()
                            .buildBoard()
                            .setRandomTurn(randomTurn);


                    gameBuilder.initTurnIterator(); // turn iterator has to be initialized before create player of game

                    if(mode == MillGame.GameMode.HUMAN_ROBOT){
                        gameBuilder.setRobotLevel(robotLevel);
                    }

                    MillGame game = gameBuilder
                            .createPlayers(mode, pieceColor)
                            .build();

                    GameGUIMinimal gameGUI = new GameGUIMinimal(game)
                            .setBoardBackground(boardBackgroundColor);
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

    public static Namespace parseCmdArguments(ArgumentParser parser, String[] args) throws ArgumentParserException, IOException, RankedException {
        Namespace ns = App.parseCmdArguments(parser, args);
        variant = ns.get("variant");
        mode = ns.get("mode");
        randomTurn = ns.get("randomTurn");
        //turnTime = ns.get("turnTime");
        robotLevel = ns.get("robotLevel");
        pieceColor = ns.get("pieceColor");
        //BoardPanel.BoardBackground boardBackground = new BoardPanel.BoardBackground();
        //boardBackgroundColor = boardBackground.getColor(ns.get("boardColor"));
        boardBackgroundColor = new java.awt.Color(128, 64, 32);

        return ns;
    }
}
