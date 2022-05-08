package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.logging.TraceLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class MillGame {
    private TurnIterator turniter;
    private ArrayList<Player> players;
    private Board board;
    private EventAction eventAction;
    private GameStageIterator stageIterator;

    public MillGame(){ // useless constructor, to create a MillGame object use MillGameBuilder class
        turniter = null;
        players = null;
        board = null;
        stageIterator = GameStageIterator.init();
    }

    public GameStage nextStage(){ return stageIterator.next(); }
    public Player nextTurn(){ return turniter.next(); }
    public Player getActivePlayer(){
        return turniter.getIterationState();
    }
    public Player getOpponentPlayer(){ return turniter.getOpponent(); }
    public boolean isGameOver(){ return false; }


    public void setTurnIterator(TurnIterator itr){ turniter = itr; }
    public void setPlayers(ArrayList<Player> players){ this.players = players; }
    public void setBoard(Board board){ this.board = board; }
    public Board getBoard(){ return board; }

    public BoardPanel getBoardPanel() { return board.getPanel(); }

    public void changeEventAction(EventAction eventAction){
        this.eventAction = eventAction;
        eventAction.setGame(this);

        TraceLogger.log(Level.INFO, "Game event action changed to " + eventAction, MillGame.class);

        board.unmark();
        Position origin = board.getOrigin();
        origin.setEventAction(eventAction);
    }

    /*
     * Inner enumerations
     */

    public enum GameMode {
        HUMAN_HUMAN,
        HUMAN_ROBOT
    }
    public enum GameVariant {
        THREE_MEN_MORRIS,
        SIX_MEN_MORRIS,
        NINE_MEN_MORRIS,
        TWELVE_MEN_MORRIS
    }

    public enum GameStage {
        POSITIONING, // positioning pieces stage
        PLAYING, // moving, removing pieces stages
        GAMEOVER // end of game
    }
}
