package com.example.millgame.exceptions;

import com.example.millgame.Piece;
import com.example.millgame.Position;

public class InvalidBoardPiece extends RankedException {
    // this exception is used to handle the following problem:
    // manipulate a piece that does not belong to the board

    public InvalidBoardPiece(Piece piece){
        super(getErrorMessage(piece));
    }

    public static String getErrorMessage(Piece piece){
        String error = piece + " does not belong to the board.";
        Position position = piece.getPosition();

        if(position != null){
            error += " Piece position: " + position;
        }

        return error;
    }

}
