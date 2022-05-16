package com.example.millgame;

import com.example.millgame.exceptions.*;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.players.PlayerType;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.pieces.PieceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class Player {
    private List<Piece> pieces;
    public final int npieces;
    private int placedPieces;

    private final PlayerType playerType;

    private final PieceColor pieceColor;
    protected final MillGame game;

    private final Board board;


    public Player(PlayerType playerType, PieceColor color, MillGame game) {
        this.game = game;
        board = game.getBoard();

        npieces = board.getNumberPlayerPieces();
        this.playerType = playerType;
        pieceColor = color;
        pieces = new ArrayList<Piece>(); // no pieces were placed to board
        placedPieces = 0;
    }

    public Player(PlayerType playerType, PieceColor color, Board board){
        game = null;
        this.board = board;
        npieces = board.getNumberPlayerPieces();
        this.playerType = playerType;
        pieceColor = color;
        pieces = new ArrayList<Piece>(); 
        placedPieces = 0;
    }

    public int getPlacedPieces(){ return placedPieces; }

    public void placePiece(char x, int y)
            throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        //System.out.println(toString());
        /*if(false) {
            throw new InvalidPositionCoordinate(x, y);
        }*/

        if(placedPieces >= board.getNumberPlayerPieces()) {
            throw new NoPiecesError(pieceColor, MillGame.GameStage.POSITIONING, Level.WARNING);
        }

        Piece piece = PieceFactory.create(pieceColor);
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

        Position selectedPosition = board.getPosition(x, y);

        if (!piece.getPosition().hasNeighbour(selectedPosition)) {
            throw new InvalidMovement(piece.getPosition(), selectedPosition);
        }

        if (selectedPosition.hasPiece()) {
            throw new NotEmptyPosition(selectedPosition);
        }

        char pieceXLabel = piece.getPosition().getXLabel();
        int pieceYLabel = piece.getPosition().getYLabel();
        board.removePiece(pieceXLabel, pieceYLabel);
        board.placePiece(piece, x, y);
        //END
    }

    public void movePiece(Piece piece, Position position) throws RankedException{
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        movePiece(piece, xLabel, yLabel);
    }
    public int removePiece(char xLabel, int yLabel) throws RankedException {
        // this code was wrote just for testing RemovingEventAction (REMOVE or REUSE)
        // this code is not intended to handle all the possible cases, just to work
        // NOTE: remove throws RankedException and specify the specific exceptions
        // * verify that active turn belongs to the opponent
        // BEGIN
        Position position = board.getPosition(xLabel, yLabel);
        Piece piece = position.getPiece();
        if(piece != null && hasPiece(piece)){
            board.removePiece(xLabel, yLabel);
            pieces.remove(piece);
        }

        return pieces.size();
        //END
    }

    public int removePiece(Position position) throws RankedException {
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        return removePiece(xLabel, yLabel);
    }

    public List<Position> getPossibleMovements(Piece piece){
        ArrayList<Position> possibleMovements = new ArrayList<Position>();

        if(pieces.size() == 3){
            possibleMovements.addAll(board.getEmptyPositions());
        } else {
            Position position = piece.getPosition();
            possibleMovements.addAll(position.getNeighbours());
        }

        return possibleMovements;
    }

    public int countBoardPieces(){ return pieces.size(); }

    public Piece getPiece(char x, char y) throws InvalidPositionCoordinate {
        Position position = board.getPosition(x, y);
        Piece piece = null;

        if(position != null){
            piece = position.getPiece();
        }

        return piece;
    }

    public PieceColor getColor(){  return pieceColor; }

    public boolean hasPiece(Piece piece){ return pieces.contains(piece); }

    @Override
    public String toString() {
        String out = "Player(color:" + pieceColor + ", type: " + playerType +
                ", placedPieces: " + placedPieces + ", boardPieces: " + pieces.size() + ")";
        return out;
    }
}