package com.example.millgame.strategy;

import com.example.millgame.MillGame;

public class GameStep {
    private MillGame game;
    private GameAction action;
    private GameStep nextStep;

    public GameStep (MillGame game) {
        this.game = game;
    }

    public MillGame getGame() {
        return game;
    }
}