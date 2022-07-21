package com.example.millgame.strategy;

import com.example.millgame.MillGame;
import com.example.millgame.actions.EventAction;

public abstract class GameStepAction {
    //protected EventAction action;
    private MillGame game;
    private GameAction action;
    private MillGame nextGame;

    public MillGame process() {
        return null;
    }
    
}