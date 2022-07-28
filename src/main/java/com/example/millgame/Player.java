/*
 * Abstracción de un jugador de NineMorris
 *
 *
 * Date: 2 de julio
 */

package com.example.millgame;

import com.example.millgame.exceptions.*;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.players.PlayerType;
import com.example.millgame.misc.Color;
import com.example.millgame.pieces.PieceFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class Player {
  public final int gamePieces;
  protected List<Piece> pieces;
  protected int placedPieces;
  protected PlayerType type;
  protected final Color color;
  protected final MillGame game;
  protected final Board board;

  public Player(PlayerType playerType, Color color, MillGame game) {
    this.game = game;
    board = game.getBoard();

    gamePieces = game.getNumberPlayerPieces();
    this.type = playerType;
    this.color = color;
    pieces = new ArrayList<Piece>(); // no pieces were placed to board
    placedPieces = 0;
  }


  /*
   * Operaciones sobre piezas
   */
  public int getPlacedPieces(){ return placedPieces; }

  public Piece getPiece(char x, int y) throws InvalidPositionCoordinate, NotOwnPiece {
    // iterate over pieces -> piece.getPosition() == (x, y) -> return piece
    Piece piece = game.getPiece(x, y);

    for (Piece p: pieces) {
      if (p == piece) {
        return piece;
      }
    }

    throw new NotOwnPiece(piece);
  }

  public List<Piece> getBoardPieces(){ return pieces; }

  public void placePiece(char x, int y)
      throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {

    if(placedPieces >= gamePieces) {
      throw new NoPiecesError(color, MillGame.GameStage.POSITIONING, Level.WARNING);
    }

    Piece piece = PieceFactory.create(color);
    board.placePiece(piece, x, y);
    pieces.add(piece);
    placedPieces += 1;

    TraceLogger.log(Level.INFO, this + " placed a piece in (" + x + ", " + y + ") position");
  }

  public void placePiece(Position position)
      throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
    char xLabel = position.getXLabel();
    int yLabel = position.getYLabel();

    placePiece(xLabel, yLabel);
  }

  public void movePiece(Piece piece, char x, int y)
      throws NotOwnPiece, NotEmptyPosition,
      InvalidPositionCoordinate, EmptyPositionError, InvalidMovement {

    if (!hasPiece(piece)) {
      throw new NotOwnPiece(piece);
    }

    Position position = piece.getPosition();
    Position selectedPosition = board.getPosition(x, y);

    if (selectedPosition.hasPiece()) {
      throw new NotEmptyPosition(selectedPosition);
    }

    List<Position> possibleMovements = board.getPossibleMovements(piece);
    System.out.println("POSSIBLE MOVEMENTS (" + piece + "): " + possibleMovements);//

    if (!possibleMovements.contains(selectedPosition)) {
      throw new InvalidMovement(position, selectedPosition);
    }

    char pieceXLabel = position.getXLabel();
    int pieceYLabel = position.getYLabel();
    board.removePiece(pieceXLabel, pieceYLabel);
    board.placePiece(piece, x, y);
  }

  public void movePiece(Piece piece, Position position) throws RankedException{
    char xLabel = position.getXLabel();
    int yLabel = position.getYLabel();

    movePiece(piece, xLabel, yLabel);
  }

  public void removePiece(Piece piece)
      throws NotOwnPiece, InvalidPositionCoordinate, EmptyPositionError, RemovePieceFromMillError {

    if (!hasPiece(piece)) {
      throw new NotOwnPiece(piece);
    }

    // board.getMills(piece).size() -> board.inAnyMill(piece)
    if (board.inAnyMill(piece) && !VerifyAllPiecesFormMills()) {
      throw new RemovePieceFromMillError(piece);
    }

    Position position = piece.getPosition();

    board.removePiece(position.getXLabel(), position.getYLabel()); // Elimina tambien la pieza de la lista de molinos a la que pertenece
    pieces.remove(piece);

  }

  /*
   * Operaciones sobre piezas
   */

  public int countBoardPieces(){ return pieces.size(); }

  public boolean hasPiece(Piece piece){ return pieces.contains(piece); }

  public boolean VerifyAllPiecesFormMills() {
    for (Piece piece: pieces) {
      if (!board.inAnyMill(piece))
        return false;
    }
    return true;
  }


  /*
   * Movimientos del jugador
   */

  public boolean hasPossibleMovement(){
    boolean result = false;
    for(Piece piece : pieces) {
      List<Position> possibleMovements = board.getPossibleMovements(piece);
      if(possibleMovements.size() > 0){
        result = true;
        break;
      }
    }

    return result;
  }

  /*
   * Metodos sobre el objeto player
   */

  public PlayerType getType(){ return type; }

  public Color getColor(){  return color; }

  public ImageIcon getPieceIcon() {
    Piece piece = PieceFactory.create(color);
    return piece.getNormalIcon();
  }

  @Override
  public String toString() {
    String out = "Player(color:" + color + ", type: " + type + ", gamePieces:" + gamePieces +
        ", placedPieces: " + placedPieces + ", boardPieces: " + pieces.size() + ")";
    return out;
  }
}