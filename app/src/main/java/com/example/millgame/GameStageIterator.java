package com.example.millgame;

import com.example.millgame.logging.TraceLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class GameStageIterator extends CircularIterator<MillGame.GameStage>{
    private GameStageIterator(ArrayList<MillGame.GameStage> gameStages){
        super(gameStages, false);
    }

    public static GameStageIterator init(){
        ArrayList<MillGame.GameStage> gameStages = new ArrayList<MillGame.GameStage>();
        gameStages.add(MillGame.GameStage.POSITIONING);
        gameStages.add(MillGame.GameStage.PLAYING);
        gameStages.add(MillGame.GameStage.GAMEOVER);

        return new GameStageIterator(gameStages);
    }

    @Override
    public MillGame.GameStage next(){
        MillGame.GameStage stage = super.next();
        TraceLogger.log(Level.INFO, "Game stage changed to " + stage, GameStageIterator.class);
        return stage;
    }
}
