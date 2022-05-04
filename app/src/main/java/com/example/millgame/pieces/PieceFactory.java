package com.example.millgame.pieces;

import com.example.millgame.Piece;
import com.example.millgame.Position;

public class PieceFactory {
    public static Piece create(PieceColor color){
        PieceFactory factory = new PieceFactory();
        Piece piece = null;
        switch (color)
        {
            case WHITE:
                piece = factory.createWritePiece();
                break;
            case BLACK:
                piece = factory.createBlackPiece();
                break;
        }
        return piece;
    }

    public static Piece create(PieceColor color, Position position){
        PieceFactory factory = new PieceFactory();
        Piece piece = null;
        switch (color)
        {
            case WHITE:
                piece = factory.createWritePiece(position);
                break;
            case BLACK:
                piece = factory.createBlackPiece(position);
                break;
        }
        return piece;
    }

    public Piece createWritePiece(){
        return new WhitePiece();
    }

    public Piece createBlackPiece(){
        return new BlackPiece();
    }

    public Piece createWritePiece(Position position){
        return new WhitePiece();
    }

    public Piece createBlackPiece(Position position){
        return new BlackPiece();
    }
}
