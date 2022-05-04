package com.example.millgame;

import java.util.List;
import java.util.Random;

public class TurnIterator extends CircularIterator<Player>{

    TurnIterator(List<Player> collection) {
        super(collection, false);
    }
    public Player getOpponent() throws CloneNotSupportedException {
        TurnIterator itr = (TurnIterator) this.clone();

        return itr.next();
    }
}
