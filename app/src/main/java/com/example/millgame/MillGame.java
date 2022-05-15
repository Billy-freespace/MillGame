package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.players.PlayerFactory;
import com.example.millgame.players.PlayerType;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MillGame {
    private TurnIterator turniter;
    private List<Player> players;
    private Board board;
    private BoardPanel boardPanel;
    private EventAction eventAction;
    private GameStageIterator stageIterator;

    public MillGame(){ // useless constructor, to create a MillGame object use MillGameBuilder class
        turniter = new TurnIterator();
        players = new ArrayList<Player>();
        board = null;
        stageIterator = GameStageIterator.init();
        eventAction = null;
    }

    public void initTurn(boolean random){
        turniter.reset(random);
        turniter.next();
    }

    public GameStage nextStage(){ return stageIterator.next(); }
    public GameStage getStage(){ return  stageIterator.getIterationState(); }
    public Player nextTurn(){ return turniter.next(); }
    public Player getActivePlayer(){
        return turniter.getIterationState();
    }
    public Player getOpponentPlayer(){ return turniter.getOpponent(); }
    public boolean isGameOver(){ return false; }

    public void setPlayers(List<Player> players){ this.players = players; }
    public void addPlayer(PlayerType playerType, PieceColor color) throws RankedException{
        if(turniter.size() > 2){
            throw new RankedException("Limit of players reached", Level.WARNING); // NOTE: write an exception for handle this case
        }

        for(Player player : turniter.values()){
            if(player.getColor() == color){
                throw new RankedException("Color " + color + " was already taken by a player", Level.WARNING); // NOTE: write an exception for handle this case
            }
        }

        Player player = PlayerFactory.create(playerType, color, board);
        turniter.addPlayer(player);
    }
    public void setBoard(Board board){ this.board = board; }

    public void setBoardPanel(BoardPanel boardPanel){ this.boardPanel = boardPanel;}
    public Board getBoard(){ return board; }

    public BoardPanel getBoardPanel() { return boardPanel; }

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
