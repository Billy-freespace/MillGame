package com.example.millgame.exceptions;

import com.example.millgame.Piece;
import com.example.millgame.Position;

import java.util.List;

public class InvalidMill extends RankedException {
    public InvalidMill(List<Piece> pieces){
        super(getErrorMessage(pieces));
    }

    public static String getErrorMessage(List<Piece> pieces){
        String error = "Supplied pieces do not form a mill. Position of pieces: ";
        for(Piece piece: pieces){
            Position position = piece.getPosition();
            error += position + ", ";
        }
        return error;
    }
}
