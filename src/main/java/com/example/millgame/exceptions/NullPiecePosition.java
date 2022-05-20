package com.example.millgame.exceptions;

import com.example.millgame.Piece;

import java.util.logging.Level;

public class NullPiecePosition extends RankedException{
    public NullPiecePosition(Piece piece){
        super(NullPiecePosition.getErrorMessage(piece));
    }
    public NullPiecePosition(Piece piece, Level rank){
        super(NullPiecePosition.getErrorMessage(piece), rank);
    }

    private static String getErrorMessage(Piece piece){
        return "Piece position is null" +
                " (Piece wasn't placed in board or was deleted)";
    }
}
