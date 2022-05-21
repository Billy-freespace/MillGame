package com.example.millgame.turns;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
public class TimedTurnIterator{

    Timer timer;

    public TimedTurnIterator(TurnIterator turnIterator, int turnTime){ // turnTime is in seconds
        super(turnIterator);

        int time = 1000*turnTime; // time is in milliseconds;

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                next();
            }
        };

        timer = new Timer(time, listener);
    }

    void start(){ timer.start(); }
    void restartTimer(){ timer.restart();}
}
 */