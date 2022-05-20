package com.example.millgame.exceptions;

import com.example.millgame.Piece;
import com.example.millgame.Position;

import java.util.List;

public class InvalidMillColor extends RankedException {
    public InvalidMillColor(List<Piece> pieces){
        super(getErrorMessage(pieces));
    }

    public static String getErrorMessage(List<Piece> pieces){
        String error = "Supplied pieces do not have the same color. Pieces: ";
        for(Piece piece: pieces){
            error += piece + ", ";
        }
        return error;
    }
}