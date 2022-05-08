package com.example.millgame.pieces;

import com.example.millgame.Piece;

public class PieceFactory {
    public static Piece create(PieceColor color){
        Piece piece = null;
        switch (color)
        {
            case WHITE:
                piece = createWritePiece();
                break;
            case BLACK:
                piece = createBlackPiece();
                break;
        }
        return piece;
    }

    public static Piece createWritePiece(){
        return new WhitePiece();
    }

    public static Piece createBlackPiece(){
        return new BlackPiece();
    }
}
