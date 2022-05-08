package com.example.millgame;

import java.util.ArrayList;

public class GameStageIterator extends CircularIterator<MillGame.GameStage>{
    private GameStageIterator(ArrayList<MillGame.GameStage> gameStages){
        super(gameStages, false);
    }

    public static GameStageIterator create(){
        ArrayList<MillGame.GameStage> gameStages = new ArrayList<MillGame.GameStage>();
        gameStages.add(MillGame.GameStage.UNINITIATED);
        gameStages.add(MillGame.GameStage.POSITIONING);
        gameStages.add(MillGame.GameStage.PLAYING);
        gameStages.add(MillGame.GameStage.GAMEOVER);

        return new GameStageIterator(gameStages);
    }
    public void reset(){
        this.iterationState = null;
        this.iterationIndex = 0;
    }
}
