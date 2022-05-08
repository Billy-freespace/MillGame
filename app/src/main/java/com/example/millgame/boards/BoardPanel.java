package com.example.millgame.boards;

import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class BoardPanel extends JPanel {
    public static char MIN_XLABEL;
    public static char MAX_YLABEL;

    protected Position origin;
    protected HashMap<Character, HashMap<Integer, Position>> positions;

    public BoardPanel(){
        positions = new HashMap<Character, HashMap<Integer, Position>>();
    }
    public HashMap<Character, HashMap<Integer, Position>> getPositions(){ return positions; }

    public Position getOrigin(){ return origin; }

    public void addPosition(Position position){
        char xLabel = position.getXLabel();
        int yLabel = position.getYLabel();

        if(!positions.containsKey(xLabel)){
            positions.put(xLabel, new HashMap<Integer, Position>());
        }

        HashMap<Integer, Position> inner = positions.get(xLabel);
        inner.put(yLabel, position);
    }

    public Position getPosition(char xLabel, int yLabel) {
        Position position = null;
        try {
            if(!positions.containsKey(xLabel)){
                throw new InvalidPositionCoordinate(xLabel, yLabel);
            }

            HashMap<Integer, Position> inner = positions.get(xLabel);
            if(!inner.containsKey(yLabel)){
                throw new InvalidPositionCoordinate(xLabel, yLabel);
            }

            position = inner.get(yLabel);
        }
        catch (InvalidPositionCoordinate error){
            // LOG ERROR
            position = null;
        }

        return position;
    }
}
