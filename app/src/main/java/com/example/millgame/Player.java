package com.example.millgame;

import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.pieces.PieceFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Piece> pieces;
    private int npieces;
    private PieceColor pieceColor;
    private Board board;

    public Player(PieceColor color, Board board){
        this.board = board;
        GameVariant variant = board.getGameVariant();
        npieces = Board.getNumberPieces(variant);
        pieceColor = color;
        pieces = new ArrayList<Piece>(); // no pieces were placed to board
    }
    public void placePiece(char x, char y) throws InvalidPositionCoordinate{
        Piece piece = PieceFactory.create(pieceColor);
        board.placePiece(piece, x, y);
    }
    public void movePiece(Piece piece, char x, char y) throws InvalidPositionCoordinate{
        Position position = piece.getPosition();
        board.removePiece(position);
        board.placePiece(piece, x, y);
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