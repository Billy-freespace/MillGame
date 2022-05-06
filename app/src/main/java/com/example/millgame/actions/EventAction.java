package com.example.millgame.actions;

import com.example.millgame.MillGame;

import java.awt.event.ActionEvent;

public abstract class EventAction {
    protected MillGame game;
    public abstract void performAction(ActionEvent event);
    public void setGame(MillGame game){ this.game = game; }
}
