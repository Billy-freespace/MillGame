package com.example.millgame;

import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NotEmptyPosition;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.players.PlayerType;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.pieces.PieceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class Player {
    private List<Piece> pieces;
    public int npieces;
    private int placedPieces;

    private PlayerType playerType;

    private PieceColor pieceColor;
    protected MillGame game;

    private Board board;


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

    public void placePiece(char x, int y) throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
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

    public void placePiece(Position position) throws NotEmptyPosition, NoPiecesError, InvalidPositionCoordinate {
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        placePiece(xLabel, yLabel);
    }

    public void movePiece(Piece piece, char x, int y) throws RankedException {
        // this code was wrote just for testing MovingEventAction (REMOVE or REUSE)
        // this code is not intended to handle all the possible cases, just to work
        // NOTE: remove throws RankedException and specify the specific exceptions
        //          and validate that the piece can only be moved to allowed positions (neighbours positions)
        // BEGIN
        Position selectedPosition = piece.getPosition();
        char selectedXLabel = selectedPosition.getXLabel();
        int selectedYLabel = selectedPosition.getYLabel();
        board.removePiece(selectedXLabel, selectedYLabel);
        board.placePiece(piece, x, y);
        //END
    }

    public void movePiece(Piece piece, Position position) throws RankedException{
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        movePiece(piece, xLabel, yLabel);
    }
    public void removePiece(char xLabel, int yLabel) throws RankedException {
        // this code was wrote just for testing RemovingEventAction (REMOVE or REUSE)
        // this code is not intended to handle all the possible cases, just to work
        // NOTE: remove throws RankedException and specify the specific exceptions
        // BEGIN
        Position position = board.getPosition(xLabel, yLabel);
        Piece piece = position.getPiece();
        if(piece != null && hasPiece(piece)){
            board.removePiece(xLabel, yLabel);
            pieces.remove(piece);
        }
        //END
    }

    public void removePiece(Position position) throws RankedException {
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        removePiece(xLabel, yLabel);
    }


    //public List<Mill> getMills(){ return null; } //sprint 2
    public Piece getPiece(char x, char y) throws InvalidPositionCoordinate {
        Position position = board.getPosition(x, y);
        Piece piece = null;

        if(position != null){
            piece = position.getPiece();
        }

        return piece;
    }

    public boolean hasPiece(Piece piece){ return pieces.contains(piece); }

    @Override
    public String toString() {
        String out = "Player(color:" + pieceColor + ", type: " + playerType +
                ", placedPieces: " + placedPieces + ", boardPieces: " + pieces.size() + ")";
        return out;
    }
}