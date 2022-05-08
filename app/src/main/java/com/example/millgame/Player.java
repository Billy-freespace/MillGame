package com.example.millgame;

import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.exceptions.NoPiecesError;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.pieces.PieceFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Piece> pieces;
    private int npieces;
    private PieceColor pieceColor;
    protected MillGame game;

    private Board board;

    public Player(PieceColor color, MillGame game){
        this.game = game;
        board = game.getBoard();
        GameVariant variant = board.getGameVariant();
        npieces = Board.getNumberPieces(variant);
        pieceColor = color;
        pieces = new ArrayList<Piece>(); // no pieces were placed to board
    }
    public void placePiece(char x, int y) throws InvalidPositionCoordinate{
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        Piece piece = PieceFactory.create(pieceColor);
        board.placePiece(piece, x, y);
    }

    public void placePiece(Position position) throws InvalidPositionCoordinate{
        // raise NoPiecesError exception if the player
        // has no piece to positioning in the POSITIONING game stage
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        placePiece(xLabel, yLabel);
    }

    public void movePiece(Piece piece, char x, int y) throws InvalidPositionCoordinate{
        Position position = piece.getPosition();
        board.removePiece(position);
        board.placePiece(piece, x, y);
    }

    public void movePiece(Piece piece, Position position) throws InvalidPositionCoordinate{
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        movePiece(piece, xLabel, yLabel);
    }
    public void removePiece(Piece piece){
        Position position = piece.getPosition();
        board.removePiece(position);
        pieces.remove(piece);
    }
    public List<Mill> getMills(){return null;}
    public Piece getPiece(char x, char y) throws InvalidPositionCoordinate {
        Position position = board.getPosition(x, y);
        Piece piece = null;

        if(position != null){
            piece = position.getPiece();
        }

        return piece;
    }
}