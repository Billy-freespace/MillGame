package com.example.millgame;

import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.players.PlayerType;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.MillGame.GameVariant;
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

        GameVariant variant = board.getVariant();
        npieces = Board.getNumberPieces(variant);
        this.playerType = playerType;
        pieceColor = color;
        pieces = new ArrayList<Piece>(); // no pieces were placed to board
        placedPieces = 0;
    }

    public Player(PlayerType playerType, PieceColor color, Board board){
        game = null;
        this.board = board;
        GameVariant variant = board.getVariant();
        npieces = Board.getNumberPieces(variant);
        this.playerType = playerType;
        pieceColor = color;
        pieces = new ArrayList<Piece>(); 
        placedPieces = 0;
    }

    public int getPlacedPieces(){ return placedPieces; }

    public void placePiece(char x, int y) throws InvalidPositionCoordinate, NoPiecesError {
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        //System.out.println(toString());
        /*if(false) {
            throw new InvalidPositionCoordinate(x, y);
        }*/

        if(placedPieces >= npieces) {
            throw new NoPiecesError(pieceColor, MillGame.GameStage.POSITIONING, Level.WARNING);
        }

        Piece piece = PieceFactory.create(pieceColor);
        board.placePiece(piece, x, y);
        pieces.add(piece);
        placedPieces += 1;

        TraceLogger.log(Level.INFO, this + " placed a piece in (" + x + ", " + y + ") position");

        //CODIGO DE PRUEBA - REMOVER
        System.out.println("x: " + x + " , y: "  + y );
        System.out.println("Numero de piezas en el tablero: " + placedPieces);
        System.out.println("---------------------------");
        //////
    }

    public void placePiece(Position position) throws InvalidPositionCoordinate, NoPiecesError{
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        placePiece(xLabel, yLabel);
    }

    public void movePiece(Piece piece, char x, int y) throws InvalidPositionCoordinate{ // sprint 2 - RECHECK
        Position position = piece.getPosition();
        board.removePiece(position);
        board.placePiece(piece, x, y);
    }

    public void movePiece(Piece piece, Position position) throws InvalidPositionCoordinate{ // sprint 2 - RECHECK
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        movePiece(piece, xLabel, yLabel);
    }
    public void removePiece(Piece piece){ // sprint 2 - RECHECK
        Position position = piece.getPosition();
        board.removePiece(position);
        pieces.remove(piece);
    }
    public List<Mill> getMills(){ return null; } //sprint 2
    public Piece getPiece(char x, char y) throws InvalidPositionCoordinate {
        Position position = board.getPosition(x, y);
        Piece piece = null;

        if(position != null){
            piece = position.getPiece();
        }

        return piece;
    }

    @Override
    public String toString() {
        String out = "Player(color:" + pieceColor + ", type: " + playerType +
                ", placedPieces: " + placedPieces + ", boardPieces: " + pieces.size() + ")";
        return out;
    }
}