package com.example.millgame.strategy;

import com.example.millgame.MillGame;
import com.example.millgame.actions.EventAction;

import java.awt.event.ActionEvent;

public abstract class GameAction {
    protected MillGame game;

    public GameAction(MillGame game) {
        this.game = game;
    }

    abstract public void performActions();

    protected void performAction(ActionEvent event) {
        EventAction eventAction = game.getEventAction();
        eventAction.actionPerformed(event);
    }

}