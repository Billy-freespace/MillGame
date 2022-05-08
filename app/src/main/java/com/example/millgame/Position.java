package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.logging.Level;

interface PositionEventAction{
    public void setEventAction(EventAction eventAction);
}

public class Position extends JButton implements PositionEventAction {
    public static ImageIcon NORMAL_ICON = new ImageIcon("textures/nmm_point-normal.png");
    public static ImageIcon PRESSED_ICON = new ImageIcon("textures/nmm_point-pressed.png");

    private char xLabel;
    private int yLabel;
    private Piece piece;
    private ArrayList<Position> neighbours;
    public boolean mark = false;

    private EventAction eventAction = null;

    public Position(char xLabel, int yLabel) {
        super(xLabel + Integer.toString(yLabel), Position.NORMAL_ICON);
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        neighbours = new ArrayList<Position>();
        piece = null;
        mark = false;

        ActionListener eventActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eventAction.actionPerformed(actionEvent);
            }
        };

        addActionListener(eventActionListener);

        TraceLogger.log(Level.INFO, "Position created: " + this, Position.class);
    }

    public Position(Point point, char xLabel, int yLabel){}
    public void addNeighbour(Position position){
        neighbours.add(position);
        TraceLogger.log(Level.INFO, position + "added as " + this + " neighbour");
    }
    public ArrayList<Position> getNeighbours(){
        return neighbours;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece() {
        return piece;
    }

    public boolean hasPiece(){ return !(piece == null) ; }
    public void setPoint(Point point){}
    public void setMark(boolean value){}

    public char getXLabel(){ return xLabel; }
    public int getYLabel(){ return yLabel; }

    public void setEventAction(EventAction eventAction){
        TraceLogger.log(Level.FINE,
                "(" + this + ") Event action changed to " + eventAction,
                Position.class);

        this.eventAction = eventAction;
        mark = true;


        TraceLogger.log(Level.FINE,
                "(" + this + ") Propagate event action to neighbours positions",
                Position.class);

        for(Position neighbour : neighbours){
            if(!neighbour.mark){
                neighbour.setEventAction(eventAction);
            }
        }
    }

    @Override
    public String toString() {
        return "Position(x:" + xLabel + ", y:" + yLabel + ")";
    }
}
