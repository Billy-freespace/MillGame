package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Player;
import com.example.millgame.actions.EventAction;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Color;
import com.example.millgame.actions.*;
import com.example.millgame.turns.TurnIterator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Random;
import java.util.logging.Level;

public abstract class RobotPlayer extends Player {
    private RobotLevel level;
    protected Random random;

    protected ActionListener turnListener;

    public RobotPlayer(Color color, MillGame game, RobotLevel level)
    {
        super(PlayerType.ROBOT, color, game);
        this.level = level;
        random = new Random();

        turnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Player activePlayer = (Player) actionEvent.getSource();

                TraceLogger.log(Level.INFO, "RobotPlayer.ActionListener : " + actionEvent);
                if( activePlayer.getType() == PlayerType.ROBOT &&
                    getCls() == (RobotPlayer) activePlayer){
                    autoPlay();
                }
            }
        };
    }

    private RobotPlayer getCls(){ return this; }

    public void autoPlay(){
        EventAction currentEventAction = game.getEventAction();
        TraceLogger.log(Level.INFO, "RobotPlayer.autoPlay method, currentEventAction: " + currentEventAction);
        try {
            Thread.sleep(60);
            switch (currentEventAction.getActionType()) {
                case POSITIONING:
                    autoPlacePiece();
                    break;
                case MOVING:
                    autoMovePiece();
                    break;
                case REMOVING:
                    autoRemovePiece();
                    break;
            }
        } catch (Exception error){
            RankedException rankedException = new RankedException(error, Level.WARNING);
            TraceLogger.log(rankedException);
        }
    }

    public ActionListener getTurnListener(){ return turnListener; }

    public abstract void autoPlacePiece();
    public abstract void autoMovePiece();
    public abstract void autoRemovePiece();
}
