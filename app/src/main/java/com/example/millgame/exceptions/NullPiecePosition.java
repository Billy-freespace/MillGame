package com.example.millgame.exceptions;

import com.example.millgame.Piece;

public class NullPiecePosition extends Exception{
    public NullPiecePosition(Piece piece){
        super(NullPiecePosition.getErrorMessage(piece));
    }

    private static String getErrorMessage(Piece piece){
        String message = "Piece position is null";
        message += " (Piece wasn't placed in board or was deleted)";

        return message;
    }
}
