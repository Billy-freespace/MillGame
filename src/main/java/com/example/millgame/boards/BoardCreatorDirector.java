package com.example.millgame.boards;

import com.example.millgame.MillGame.GameVariant;
import com.example.millgame.Board;
import com.example.millgame.logging.TraceLogger;

import java.util.logging.Level;

public class BoardCreatorDirector {
    public static Board makeMMBoard(GameVariant variant){
        Board board;
        switch (variant){
            case THREE_MEN_MORRIS:
                board = new ThreeMMBoardBuilder().build();
                break;

            case SIX_MEN_MORRIS:
            case FIVE_MEN_MORRIS:
                board = new SixMMBoardBuilder().build();
                break;

            case SEVEN_MEN_MORRIS:
                board = new SevenMMBoardBuilder().build();
                break;

            case NINE_MEN_MORRIS:
                board = new NineMMBoardBuilder().build();
                break;

            case ELEVEN_MEN_MORRIS:
            case TWELVE_MEN_MORRIS:
                board = new TwelveMMBoardBuilder().build();
                break;
            default:
                board = null;
        }

        return board;
    }

    public SixMMBoard makeSixMMBoard(SixMMBoardBuilder builder){
        SixMMBoard board = builder.build();
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
