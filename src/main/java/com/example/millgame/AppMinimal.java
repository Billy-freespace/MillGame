package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.misc.CmdParser;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotLevel;
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

    protected static boolean randomTurn;
    protected static RobotLevel robotLevel;

    protected static int turnTime;

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
                            .setGameMode(mode)
                            .setRandomTurn(randomTurn);

                    if(turnTime > 0){ // this is a timed turn game
                        gameBuilder.setTurnTime(turnTime);
                    }

                    com.example.millgame.misc.Color color = com.example.millgame.misc.Color.WHITE;
                    gameBuilder.createPlayer(PlayerType.HUMAN, color);

                    if(mode == MillGame.GameMode.HUMAN_ROBOT){
                        // GET OTHER SUPPLIED COLOR
                        color = com.example.millgame.misc.Color.BLACK;
                        gameBuilder.setRobotLevel(robotLevel)
                                .createPlayer(PlayerType.ROBOT, color);
                    } else{
                        gameBuilder.createPlayer(PlayerType.HUMAN, color);
                    }

                    MillGame game = gameBuilder.build();

                    GameGUIMinimal gameGUI = new GameGUIMinimal()
                            .setGame(game)
                            .setBoardBackground(new Color(128, 64, 32));
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
        variant = (MillGame.GameVariant) ns.get("variant");
        mode = (MillGame.GameMode) ns.get("mode");

        //TraceMessage message = new TraceMessage(
                Level.INFO,
                "variant: " + variant +
                        "game mode: " + mode);

        //TraceLogger.log(message);

        return ns;
    }
}
