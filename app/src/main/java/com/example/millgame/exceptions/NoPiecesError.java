package com.example.millgame.exceptions;

import com.example.millgame.MillGame;
import com.example.millgame.pieces.PieceColor;

import java.util.logging.Level;

public class NoPiecesError extends RankedException{
    // this exception is raised when a player has no enough pieces to positioning
    // if this exception is called in other game stage, then a warning message will be displayed

    public NoPiecesError(PieceColor color, MillGame.GameStage stage){
        super(NoPiecesError.getErrorMessage(color, stage));
    }

    public NoPiecesError(PieceColor color, MillGame.GameStage stage, Level rank){
        super(NoPiecesError.getErrorMessage(color, stage), rank);
    }

    public static String getErrorMessage(PieceColor color, MillGame.GameStage stage){
        String errorMessage;
        if(stage != MillGame.GameStage.POSITIONING){
            errorMessage = "WARNING: " + NoPiecesError.class.getName() +
                    " exception will be only used in POSITIONING stage (actual stage: " +
                    stage.toString() + ")";
        }
        else{
            errorMessage = "Player " + color.toString() + "has no pieces to positioning";
        }
        return errorMessage;
    }
}
