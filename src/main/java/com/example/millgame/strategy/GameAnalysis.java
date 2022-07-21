package com.example.millgame.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import com.example.millgame.*;
import com.example.millgame.actions.EventAction;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.BoardCoordinate;

public class GameAnalysis {

    private MillGame game;
    private HashMap<Piece, List <Position>> opponentMovingPossibleMills;
    private HashMap<Piece, List <Position>> ownMovingPossibleMills;
    private List<Position> opponentPositioningPossibleMills;
    private List<Position> ownPositioningPossibleMills;
    private List<GameStep> playbooks;
    private int MAX_NUM_STEPS;

    private PieceRadar radar;
    private MillGame millGameClone;

    public GameAnalysis(MillGame game) throws CloneNotSupportedException {
        this.game = game;
        millGameClone = game.clone();
        init();
    }

    private void init() {
        EventAction currentEventAction = millGameClone.getEventAction();
        List<Position> emptyPositions = millGameClone.getBoard().getEmptyPositions();
        try {
            switch (currentEventAction.getActionType()) {
                case POSITIONING:
                    for (Position pos: emptyPositions) {
                        new PositioningGameAction(millGameClone, pos).performActions();
                        playbooks.add(new GameStep(millGameClone));
                        Piece piece = millGameClone.getPiece(pos.getXLabel(), pos.getYLabel());
                        if (millGameClone.getBoard().inAnyMill(piece))
                            ownPositioningPossibleMills.add(pos);
                        millGameClone = game;
                    }
                    break;
                case MOVING:
                    ;
                    break;
                case REMOVING:
                    ;
                    break;
            }
        } catch (Exception error){
            RankedException rankedException = new RankedException(error, Level.WARNING);
            TraceLogger.log(rankedException);
        }
    }
    
    private void findPossibleSteps() {

    }

    private MillGame applyGameStep(GameStep step) {
        return null;
    }

    public void apply() {
        ;
    }

}