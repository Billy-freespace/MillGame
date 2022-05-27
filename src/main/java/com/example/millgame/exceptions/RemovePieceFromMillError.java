package com.example.millgame.exceptions;

import com.example.millgame.Piece;

import java.util.logging.Level;

public class RemovePieceFromMillError extends RankedException{
    public RemovePieceFromMillError(Piece piece) {
        super(getMessageError(piece));
    }

    public RemovePieceFromMillError(Piece piece, Level rank) {
        super(getMessageError(piece), rank);
    }

    public static String getMessageError(Piece piece) {
        return piece + ": You can't remove this piece, unless all of your opponent's pieces are forming mills.";
    }
}
