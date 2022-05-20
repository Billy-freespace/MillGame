package com.example.millgame.actions;

import com.example.millgame.MillGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class EventAction extends AbstractAction {
    protected MillGame game = null;
    private ActionType actionType;

    public EventAction(ActionType actionType){
        super();
        this.actionType = actionType;
    }
    public abstract void actionPerformed(ActionEvent event);
    public void setGame(MillGame game){ this.game = game; }

    public ActionType getActionType(){ return actionType; }
}
