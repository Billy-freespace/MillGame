package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.Assets;
import com.example.millgame.misc.CmdParser;
import com.example.millgame.misc.Color;
import com.example.millgame.players.PlayerType;
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

    protected static List<Color> colorList;

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

                    if(turnTime > 0){ // this is a timed turn game
                        gameBuilder.setTurnTime(turnTime);
                    }

                    gameBuilder.initTurnIterator();

                    com.example.millgame.misc.Color color = com.example.millgame.misc.Color.WHITE;
                    gameBuilder.createPlayer(PlayerType.HUMAN, color);

                    color = com.example.millgame.misc.Color.BLACK;
                    if(mode == MillGame.GameMode.HUMAN_ROBOT){
                        // GET OTHER SUPPLIED COLOR
                        gameBuilder.setRobotLevel(robotLevel)
                                .createPlayer(PlayerType.ROBOT, color);
                    } else{
                        gameBuilder.createPlayer(PlayerType.HUMAN, color);
                    }

                    MillGame game = gameBuilder.build();

                    GameGUIMinimal gameGUI = new GameGUIMinimal()
                            .setGame(game)
                            .setBoardBackground(new java.awt.Color(128, 64, 32));
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
        turnTime = ns.get("turnTime");
        robotLevel = ns.get("robotLevel");


        return ns;
    }
}
