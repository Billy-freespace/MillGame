package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Color;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotPlayer;
import com.example.millgame.turns.TurnIterator;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class MillGame {
    private TurnIterator turnIter;
    private Board board;
    private EventAction eventAction;
    private GameStageIterator stageIter;

    private GameVariant variant;

    private Player winner;

    private static Map<GameVariant, Integer> gameVariant2PlayerPieces = MillGame.getMapPlayerPieces();


    public MillGame(GameVariant variant){ // useless constructor, to create a MillGame object use MillGameBuilder class
        turnIter = null;
        board = null;
        stageIter = GameStageIterator.init();
        eventAction = null;
        this.variant = variant;
        winner = null;
    }

    public void initTurnIterator(boolean randomTurn){
        turnIter = new TurnIterator(randomTurn);
    }


    public int countPieces(Color color) { return board.countPieces(color); }
    public GameStage nextStage(){ return stageIter.next(); }
    public GameStage getStage(){ return  stageIter.getIterationState(); }
    public Player nextTurn(){
        Player nextOpponent;
        Player opponent = getOpponentPlayer();

        if(opponent.getPlacedPieces() == getNumberPlayerPieces() && (
                opponent.countBoardPieces() == 2 ||
                        !opponent.hasPossibleMovement())){
            winner = getActivePlayer();
            nextOpponent = winner;

            TraceLogger.log(Level.INFO, "Winner player: " + winner);
            TraceLogger.log(Level.INFO, "Active PLayer: " + getActivePlayer());
        } else {
            nextOpponent = turnIter.next();
        }

        return nextOpponent;
    }
    public Player getActivePlayer(){
        return turnIter.getIterationState();
    }
    public Player getOpponentPlayer(){
        Player opponent = null;
        try{
            TurnIterator itr = (TurnIterator) turnIter.clone();
            itr.removeAllTurnListeners(); // avoid call turn listener when calling to next method
            opponent = itr.next();
        } catch (CloneNotSupportedException error){
            RankedException exception = new RankedException(error, Level.SEVERE);
            TraceLogger.log(exception, TurnIterator.class);
        }

        return opponent;
    }

    public Piece getPiece(char x, int y) throws InvalidPositionCoordinate {
        Position position = board.getPosition(x, y);
        Piece piece = position.getPiece();

        return piece;
    }

    public GameVariant getVariant(){ return variant; }
    public int getNumberPlayerPieces(){ return MillGame.gameVariant2PlayerPieces.get(variant);}

    private static Map<GameVariant, Integer> getMapPlayerPieces(){
        Map<GameVariant, Integer> playerPieces = new HashMap<GameVariant, Integer>();
        playerPieces.put(GameVariant.THREE_MEN_MORRIS, 3);
        playerPieces.put(GameVariant.FIVE_MEN_MORRIS, 5);
        playerPieces.put(GameVariant.SIX_MEN_MORRIS, 6);
        playerPieces.put(GameVariant.SEVEN_MEN_MORRIS, 7);
        playerPieces.put(GameVariant.NINE_MEN_MORRIS, 9);
        playerPieces.put(GameVariant.ELEVEN_MEN_MORRIS, 11);
        playerPieces.put(GameVariant.TWELVE_MEN_MORRIS, 12);

        return playerPieces;
    }
    public boolean isGameOver(){ return winner != null; }

    public Player getWinner(){ return winner; }

    public List<Board.Mill> getMills(Piece piece) throws RankedException { return board.getMills(piece); }

    public void addPlayer(Player player) throws RankedException{
        if(turnIter.size() > 2){
            throw new RankedException("Limit of players reached", Level.WARNING); // NOTE: write an exception for handle this case
        }

        Color color = player.getColor();
        for(Player playerItr : turnIter.values()){
            System.out.println("Player: " + playerItr);
            if(color == playerItr.getColor()){
                throw new RankedException("Color " + color + " was already taken by a player", Level.WARNING); // NOTE: write an exception for handle this case
            }
        }

        turnIter.addPlayer(player);

        if(player.getType() == PlayerType.ROBOT){
            System.out.println("============== RESTRICT ZONE ==============");
            System.out.println("PLAYER: " + player);
            System.out.println("========================================================");
            turnIter.addTurnListener(((RobotPlayer) player).getTurnListener());
        }
    }



    public void setBoard(Board board){ this.board = board; }
    public Board getBoard(){ return board; }

    public BoardPanel getBoardPanel(){ return new BoardPanel(board); }

    public EventAction getEventAction() { return eventAction; }

    public void changeEventAction(EventAction eventAction){
        this.eventAction = eventAction;
        eventAction.setGame(this);

        TraceLogger.log(Level.INFO, "Game event action changed to " + eventAction, MillGame.class);

        board.unmark();
        Position origin = board.getOrigin();
        origin.setEventAction(eventAction);
    }

    @Override
    public String toString() {
        String out;

        out = "MillGame(variant="+ variant +
                ", board=" + board.getVariant() +
                ", stage=" + stageIter.getIterationState() + ")";
        return out;
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
        FIVE_MEN_MORRIS,
        SIX_MEN_MORRIS,
        SEVEN_MEN_MORRIS,
        NINE_MEN_MORRIS,
        ELEVEN_MEN_MORRIS,
        TWELVE_MEN_MORRIS
    }

    public enum GameStage {
        POSITIONING, // positioning pieces stage
        PLAYING, // moving, removing pieces stages
        GAMEOVER // end of game
    }
}
