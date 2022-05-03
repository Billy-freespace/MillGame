package com.example.millgame;

import java.util.List;
import java.util.Random;

abstract class CircularIterator<T> implements Cloneable {
    protected List<T> collection;
    protected int iterationIndex;
    protected T iterationState = null;

    CircularIterator(List<T> collection, boolean randomPosition){
        iterationIndex = 0;
        if(randomPosition){
            Random rand = new Random();
            int size = collection.size();
            iterationIndex = rand.nextInt(size);
        }
        this.collection = collection;
    }
    public T next(){
        if (iterationIndex == collection.size())
            iterationIndex = 0;

        iterationState = collection.get(iterationIndex);
        iterationIndex += 1;

        return iterationState;
    }
    public T getIterationState(){
        return iterationState;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
