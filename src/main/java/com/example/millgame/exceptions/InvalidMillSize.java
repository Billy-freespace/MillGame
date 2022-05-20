package com.example.millgame.exceptions;

import com.example.millgame.Piece;

import java.util.List;

public class InvalidMillSize extends RankedException {
    public InvalidMillSize(List<Piece> pieces){
        super(getErrorMessage(pieces));
    }

    public static String getErrorMessage(List<Piece> pieces){
        String error = "Invalid number of pieces to form a mill: " + pieces.size();
        return error;
    }
}
