package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Position;
import com.example.millgame.actions.ActionType;
import com.example.millgame.actions.EventAction;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Color;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;

public class NoobRobotPlayer extends RobotPlayer{

    public NoobRobotPlayer(Color color, MillGame game){super(color, game, RobotLevel.NOOB);}

    @Override
    public void autoPlacePiece(){
        List<Position> emptyPositions = board.getEmptyPositions();
        int index = random.nextInt(emptyPositions.size());
        Object position = emptyPositions.get(index);

        EventAction eventAction = game.getEventAction();

        if(eventAction.getActionType() == ActionType.POSITIONING){
            ActionEvent event = new ActionEvent(position, -1, "RobotPlayer.autoPlacePiece ["+position + "]");
            eventAction.actionPerformed(event);
        }
    }

    @Override
    public void autoMovePiece(){
        TraceLogger.log(Level.INFO, "executing autoMovePiece method", NoobRobotPlayer.class);
    }

    @Override
    public void autoRemovePiece(){
        TraceLogger.log(Level.INFO, "executing autoRemovePiece method", NoobRobotPlayer.class);
    }
}
