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
    protected List<Piece> pieces;
    public final int gamePieces;
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

    public PlayerType getType(){ return type; }

    public int getPlacedPieces(){ return placedPieces; }

    public List<Piece> getBoardPieces(){ return pieces; }

    public void placePiece(char x, int y)
            throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        //System.out.println(toString());
        /*if(false) {
            throw new InvalidPositionCoordinate(x, y);
        }*/

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
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        placePiece(xLabel, yLabel);
    }

    public void movePiece(Piece piece, char x, int y) throws NotOwnPiece, NotEmptyPosition, InvalidPositionCoordinate, EmptyPositionError, InvalidMovement {
        // this code was wrote just for testing MovingEventAction (REMOVE or REUSE)
        // this code is not intended to handle all the possible cases, just to work
        // NOTE: remove throws RankedException and specify the specific exceptions
        //          and validate that the piece can only be moved to allowed positions (neighbours positions)
        // BEGIN
        if (!hasPiece(piece)) {
            throw new NotOwnPiece(piece);
        }

        Position position = piece.getPosition();
        Position selectedPosition = board.getPosition(x, y);

        if (!position.hasNeighbour(selectedPosition)) {
            throw new InvalidMovement(position, selectedPosition);
        }

        if (selectedPosition.hasPiece()) {
            throw new NotEmptyPosition(selectedPosition);
        }

        char pieceXLabel = position.getXLabel();
        int pieceYLabel = position.getYLabel();
        board.removePiece(pieceXLabel, pieceYLabel);
        board.placePiece(piece, x, y);
        //END
    }

    public void movePiece(Piece piece, Position position) throws RankedException{
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        movePiece(piece, xLabel, yLabel);
    }
    public int removePiece(char x, int y) throws RankedException { // create a specific exception (ASAP)
        // this code was wrote just for testing RemovingEventAction (REMOVE or REUSE)
        // this code is not intended to handle all the possible cases, just to work
        // NOTE: remove throws RankedException and specify the specific exceptions
        // * verify that active turn belongs to the opponent
        // BEGIN
        Position position = board.getPosition(x, y);
        Piece piece = position.getPiece();
        if(piece == null || !hasPiece(piece)){
            throw new RankedException("Selected position is empty or piece does not belong to player");
        }

        board.removePiece(x, y);
        pieces.remove(piece);

        return pieces.size();
        //END
    }

    public int removePiece(Position position) throws RankedException {
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        return removePiece(xLabel, yLabel);
    }

    public int countBoardPieces(){ return pieces.size(); }

    public Color getColor(){  return color; }

    public boolean hasPiece(Piece piece){ return pieces.contains(piece); }

    public ImageIcon getPieceIcon(){
        Piece piece = PieceFactory.create(color);
        return piece.getNormalIcon();
    }

    @Override
    public String toString() {
        String out = "Player(color:" + color + ", type: " + type +
                ", placedPieces: " + placedPieces + ", boardPieces: " + pieces.size() + ")";
        return out;
    }
}