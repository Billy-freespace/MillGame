package com.example.millgame;

import java.util.List;
import java.util.Random;

public abstract class CircularIterator<T> implements Cloneable {
    protected List<T> collection;
    protected int iterationIndex;
    protected T iterationState = null;

    private int initIterationIndex = -1;

    CircularIterator(List<T> collection, boolean randomPosition){
        iterationIndex = -1;
        if(randomPosition){
            Random rand = new Random();
            int size = collection.size();
            iterationIndex = rand.nextInt(size);
        }
        initIterationIndex = iterationIndex;
        this.collection = collection;
    }
    public T next(){
        iterationIndex += 1;
        if (iterationIndex >= collection.size()){
            iterationIndex = 0;
        }

        iterationState = collection.get(iterationIndex);
        return iterationState;
    }
    public T getIterationState(){
        return iterationState;
    }

    protected void addIterationState(T state){
        /*
         * This method is used inside methods of subclasses of CircularIterator<T>
         */
        collection.add(state);
    }

    public int size(){
        return collection.size();
    }

    public List<T> values(){ return collection; }

    public void reset(){
        iterationIndex = initIterationIndex;
        iterationState = null;
    }

    public CircularIterator<T> clone() throws CloneNotSupportedException {
        return (CircularIterator<T>) super.clone();
    }
}
