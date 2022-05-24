package com.example.millgame.exceptions;

import com.example.millgame.Piece;

import java.util.logging.Level;

public class RemoveOwnPieceError extends RankedException{
    public RemoveOwnPieceError(Piece piece) {
        super();
    }

    public RemoveOwnPieceError(String message, Level rank) {
        super(message, rank);
    }

    public static String getErrorMessage(Piece piece) {
        return "You can't "
    }
}
