package com.example.millgame;

import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;

import java.util.List;
import java.util.logging.Level;

public class TurnIterator extends CircularIterator<Player>{

    TurnIterator(List<Player> collection) {
        super(collection, false);
    }
    public Player getOpponent(){
        Player opponent = null;
        try{
            TurnIterator itr = (TurnIterator) this.clone();
            opponent = itr.next();
        }catch (CloneNotSupportedException error){
            RankedException exception = new RankedException(error, Level.SEVERE);
            TraceLogger.log(exception, TurnIterator.class);
        }

        return opponent;
    }

    @Override
    public Player next(){
        Player player = super.next();
        TraceLogger.log(Level.INFO, "Active turn: " + player);
        return player;
    }

    public void addPlayer(Player player){
        addIterationState(player);
    }
}
