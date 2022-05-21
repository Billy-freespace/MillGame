package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.Player;
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
        Player player = game.getActivePlayer();

        int randomIndex;
        Piece piece;
        List<Position> possibleMovements;

        do {
            randomIndex = random.nextInt(pieces.size());
            piece = pieces.get(randomIndex);
            possibleMovements = board.getPossibleMovements(piece);
        } while (possibleMovements.size() == 0);

        EventAction eventAction = game.getEventAction();

        if(eventAction.getActionType() == ActionType.MOVING){
            ActionEvent event;

            Position piecePosition = piece.getPosition();
            event = new ActionEvent(piecePosition, -1,
                    "RobotPlayer.autoMovePiece (First Selection): "+ piecePosition);
            eventAction.actionPerformed(event);

            randomIndex = random.nextInt(possibleMovements.size());
            event = new ActionEvent(possibleMovements.get(randomIndex), -1,
                    "RobotPlayer.autoMovePiece (Second Selection): "+ piecePosition);

            eventAction.actionPerformed(event);
        }
    }

    @Override
    public void autoRemovePiece(){
        TraceLogger.log(Level.INFO, "executing autoRemovePiece method", NoobRobotPlayer.class);

        Player opponent = game.getOpponentPlayer();

        int randomIndex;
        Piece piece;
        List<Piece> opponentPieces = opponent.getBoardPieces();

        do{
            randomIndex = random.nextInt();
            piece = opponentPieces.get(randomIndex);
        }while(!board.inAnyMill(piece));

        EventAction eventAction = game.getEventAction();

        if(eventAction.getActionType() == ActionType.REMOVING){
            Position piecePosition = piece.getPosition();
            ActionEvent event = new ActionEvent(piecePosition, -1,
                    "RobotPlayer.autoRemovePiece : " + piecePosition);

            eventAction.actionPerformed(event);
        }
    }
}
