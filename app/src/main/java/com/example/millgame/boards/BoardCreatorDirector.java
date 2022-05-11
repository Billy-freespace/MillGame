package com.example.millgame.boards;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Board;
import com.example.millgame.logging.TraceLogger;

import java.util.logging.Level;

import static com.example.millgame.MillGame.GameVariant.*;

public class BoardCreatorDirector {
    public static Board makeMMBoard(GameVariant variant){
        TraceLogger.log(Level.INFO, "Building " + variant + " board variant", BoardCreatorDirector.class);
        Board board;
        switch (variant){
            case NINE_MEN_MORRIS:
                board = new NineMMBoardBuilder().build();
                break;
            case TWELVE_MEN_MORRIS:
                board = new TwelveMMBoardBuilder().build();
                break;
            case SIX_MEN_MORRIS:
                board = new SixMMBoardBuilder().build();
                break;
            default:
                board = null;
        }

        return board;
    }

    public NineMMBoard makeNineMMBoard(NineMMBoardBuilder builder){
        NineMMBoard board = builder.build();
        return board;
    }

    public TwelveMMBoard makeTwelveMMBoard(TwelveMMBoardBuilder builder){
        TwelveMMBoard board = builder.build();
        return board;
    }
}
