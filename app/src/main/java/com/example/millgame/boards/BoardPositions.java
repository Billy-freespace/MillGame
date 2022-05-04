package com.example.millgame.boards;

import com.example.millgame.Position;

import java.util.HashMap;

public class BoardPositions{
    private HashMap<Character, HashMap<Integer, Position>> positions;

    BoardPositions(){
        positions = new HashMap<Character, HashMap<Integer, Position>>();
    }
    public void setPosition(char xLabel, int yLabel, Position position){
        if(!positions.containsKey(xLabel)){
            positions.put(xLabel, new HashMap<Integer, Position>());
        }

        HashMap<Integer, Position> inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }

    public HashMap<Character, HashMap<Integer, Position>> getPositions(){ return positions; }
}