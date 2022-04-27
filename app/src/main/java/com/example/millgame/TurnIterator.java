package com.example.millgame;

import java.util.List;

abstract class CircularIterator<T> {
    private List<T> collection;
    private T iterationState = null;

    CircularIterator(List<T> collection, boolean randomPosition){};
    public T next(){};
    public T getIterationState(){};
}

public class TurnIterator extends CircularIterator<Player>{

    TurnIterator(List<Player> collection) {
        super(collection, false);
    }
    public Player getOponent(){};
}
