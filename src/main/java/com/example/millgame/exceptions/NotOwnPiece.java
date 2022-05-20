package com.example.millgame.exceptions;

import com.example.millgame.Piece;

import java.util.logging.Level;

public class NotOwnPiece extends RankedException {
    // this exception is raised when a player try to manipulate pieces that he does not own

    public NotOwnPiece(Piece piece){
        super(NotOwnPiece.getErrorMessage(piece));
    }

    public NotOwnPiece(Piece piece, Level level){
        super(NotOwnPiece.getErrorMessage(piece), level);
    }

    public static String getErrorMessage(Piece piece){
        String error = piece  + " does not below to player";
        return error;
    }
}
