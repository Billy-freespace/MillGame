/*
 * MillGame
 *
 * Clase principal del juego Millgame
 *
 * Date 2 de Julio 2022
 */

package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.exceptions.RemoveOwnPieceError;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Color;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotPlayer;
import com.example.millgame.turns.CircularIterator;
import com.example.millgame.turns.TurnIterator;


import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class MillGame implements Cloneable {
  private static final Map<GameVariant, Integer> gameVariant2PlayerPieces = MillGame.getMapPlayerPieces();
  private final GameVariant variant;
  private final int MAX_NUM_PLAYERS = 2;
  private TurnIterator turnIter;
  private Board board;
  private EventAction eventAction;
  private Player winner;
  private BoardPanel boardPanel = null;

  public MillGame(GameVariant variant){ // useless constructor, to create a MillGame object use MillGameBuilder class
    turnIter = null;
    board = null;
    eventAction = null;
    this.variant = variant;
    winner = null;
  }

  /*
   * Operaciones del iterador de turnos
   */

  public void initTurnIterator(boolean randomTurn){
    turnIter = new TurnIterator(randomTurn);
  }

  public void addTurnListener(ActionListener listener){ turnIter.addTurnListener(listener); }

  public Player nextTurn(){
    Player opponent = getOpponentPlayer();

    if(opponent.getPlacedPieces() == getNumberPlayerPieces() && (
        opponent.countBoardPieces() == 2 ||
            !opponent.hasPossibleMovement())){
      winner = getActivePlayer();

      TraceLogger.log(Level.INFO, "Winner player: " + winner);
    }

    return turnIter.next();
  }

  public Player notifyTurnPlayer() {
    return turnIter.notifyTurnPlayer();
  }

  /*
   * Métodos relacionados a las operaciones del jugador
   */
  public Player getActivePlayer(){
    return turnIter.getIterationState();
  }

  public Player getOpponentPlayer(){
    Player opponent = turnIter.getNextPlayer();
    return opponent;
  }
  public void addPlayer(Player player) throws RankedException{
    if(turnIter.size() > MAX_NUM_PLAYERS){
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
      turnIter.addTurnListener(((RobotPlayer) player).getTurnListener());
    }
  }

  /*
   * Operaciones de piezas
   */

  public int countPieces(Color color) { return board.getCount(color); }

  public Piece getPiece(char x, int y) throws InvalidPositionCoordinate {
    Position position = board.getPosition(x, y);
    Piece piece = position.getPiece();

    return piece;
  }

  public void removeOpponentPiece(Piece piece) throws RankedException {
    Player opponent = getOpponentPlayer();

    if (piece == null) {
      throw new NullPointerException("Trying to delete a null piece");
    }

    if(!opponent.hasPiece(piece)){
      throw new RemoveOwnPieceError(piece);
    }

    opponent.removePiece(piece);
  }

  public int getNumberPlayerPieces(){ return MillGame.gameVariant2PlayerPieces.get(variant);}

  // Refactorizar y usar polimorfismo
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

  /*
   * Operaciones con el tablero
   */

  public void setBoard(Board board){ this.board = board; }

  public Board getBoard(){ return board; }

  public BoardPanel getBoardPanel(){
    if (boardPanel == null) {
      boardPanel = new BoardPanel(board);
    }
    return boardPanel;
  }

  /*
   * Operación sobre molinos
   */

  public List<Board.Mill> getMills(Piece piece) throws RankedException { return board.getMills(piece); }

  /*
   * Operaciones relacionadas al juego
   */

  @Override
  public String toString() {
    String out;

    out = "MillGame(variant="+ variant +
        ", board=" + board.getBoardVariant() + ")";
    return out;
  }

  public EventAction getEventAction() { return eventAction; }

  public void changeEventAction(EventAction eventAction){
    this.eventAction = eventAction;
    eventAction.setGame(this);

    TraceLogger.log(Level.INFO, "Game event action changed to " + eventAction, MillGame.class);

    board.unmark();
    Position origin = board.getOrigin();
    origin.setEventAction(eventAction);
  }

  public boolean isGameOver(){ return winner != null; }

  public Player getWinner(){ return winner; }

  public GameVariant getVariant(){ return variant; }

  public MillGame clone() throws CloneNotSupportedException {
    MillGame millGame = null;
    try {
      millGame = (MillGame) super.clone();
    } catch(CloneNotSupportedException ex){
      System.out.println(" no se puede duplicar");
    }
    return millGame;
  }

  /*
   * Inner enumerations
   */

  public enum GameMode {
    HUMAN_HUMAN,
    HUMAN_ROBOT;

  }
  public enum GameVariant {
    THREE_MEN_MORRIS,
    FIVE_MEN_MORRIS,
    SIX_MEN_MORRIS,
    SEVEN_MEN_MORRIS,
    NINE_MEN_MORRIS,
    ELEVEN_MEN_MORRIS,
    TWELVE_MEN_MORRIS;
  }

  public enum GameStage {
    POSITIONING, // positioning pieces stage
    PLAYING, // moving, removing pieces stages
    GAMEOVER // end of game
  }
}
