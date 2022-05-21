package com.example.millgame.turns;

import com.example.millgame.Player;
import com.example.millgame.logging.TraceLogger;

import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TurnIterator extends CircularIterator<Player> {

    private EventListenerList turnListeners;

    public TurnIterator(boolean random) {
        super(new ArrayList<Player>(), random);
        turnListeners = new EventListenerList();
    }

    public TurnIterator(List<Player> collection, boolean random) {
        super(collection, random);
        turnListeners = new EventListenerList();
    }

    @Override
    public Player next(){
        Player player = super.next();

        ActionEvent event = new ActionEvent(player, -1, "Active Player");
        fireTurnListeners(event);

        TraceLogger.log(Level.INFO, "Active turn: " + player);
        return player;
    }

    public void addPlayer(Player player){
        addIterationState(player);
    }

    public void addTurnListener(ActionListener turnListener){
        turnListeners.add(ActionListener.class, turnListener);
    }

    private void fireTurnListeners(ActionEvent actionEvent){
        TraceLogger.log(Level.INFO, "turnListeners: " + turnListeners.getListeners(ActionListener.class), TurnIterator.class);
        TraceLogger.log(Level.INFO, "actionEvent: " + actionEvent, TurnIterator.class);
        for(ActionListener listener : turnListeners.getListeners(ActionListener.class)){
            listener.actionPerformed(actionEvent);
        }
    }

    public void removeAllTurnListeners(){ // only use this method to avoid call the turn listeners when next method is called
        turnListeners = new EventListenerList();
    }
}
