package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Player;
import com.example.millgame.actions.EventAction;
import com.example.millgame.misc.Color;
import com.example.millgame.actions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Random;

public abstract class RobotPlayer extends Player {
    private RobotLevel level;
    protected Random random;

    protected EventListener turnListener;

    public RobotPlayer(Color color, MillGame game, RobotLevel level)
    {
        super(PlayerType.ROBOT, color, game);
        this.level = level;
        random = new Random();

        turnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Player activePlayer = (Player) actionEvent.getSource();
                if( getCls() == (RobotPlayer) activePlayer){
                    autoPlay();
                }
            }
        };
    }

    private RobotPlayer getCls(){ return this; }

    public void autoPlay(){
        EventAction currentEventAction = game.getEventAction();

        switch (currentEventAction.getActionType()){
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
    }

    public EventListener getTurnListener(){ return turnListener; }

    public abstract void autoPlacePiece();
    public abstract void autoMovePiece();
    public abstract void autoRemovePiece();
}
