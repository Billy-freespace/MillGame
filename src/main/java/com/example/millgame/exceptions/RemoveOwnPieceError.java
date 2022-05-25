package com.example.millgame.exceptions;

import com.example.millgame.Piece;

import java.util.logging.Level;

public class RemoveOwnPieceError extends RankedException{
    public RemoveOwnPieceError(Piece piece) {
        super(RemoveOwnPieceError.getErrorMessage(piece));
    }

    public RemoveOwnPieceError(Piece piece, Level rank) {
        super(RemoveOwnPieceError.getErrorMessage(piece), rank);
    }

    public static String getErrorMessage(Piece piece) {
        return piece + ": You can't remove your own piece";
    }
}
