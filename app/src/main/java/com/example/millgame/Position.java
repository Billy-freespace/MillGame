package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.logging.TraceMessage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.EventListenerList;
import java.awt.*;
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

public class Position extends JButton implements PositionEventAction, ObjectIcon {
    public static final ImageIcon NORMAL_ICON = new ImageIcon("src/main/resources/textures/nmm_point-normal.png");
    public static final ImageIcon PRESSED_ICON = new ImageIcon("src/main/resources/textures/nmm_point-pressed.png");
    public static final ImageIcon ROLLOVER_ICON = new ImageIcon("src/main/resources/textures/nmm_point-hover.png");

    private char xLabel;
    private int yLabel;
    private Piece piece;
    private ArrayList<Position> neighbours;
    public boolean mark = false;

    private EventAction eventAction = null;

    public Position(char xLabel, int yLabel) {
        super(NORMAL_ICON);
        setPreferredSize(new Dimension(50, 50));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
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
        // REAL LEVEL: FINE
        TraceLogger.log(Level.INFO, position + " was added as " + this + " neighbour");
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

    public ImageIcon getNormalIcon(){ return NORMAL_ICON; }
    public ImageIcon getPressedIcon(){ return PRESSED_ICON; }
    public ImageIcon getRolloverIcon(){ return ROLLOVER_ICON; }

    @Override
    public void paintComponent(Graphics g){
        if(piece != null){
            changePositionIcons(piece);
        } else {
            changePositionIcons(this);
        }
        super.paintComponent(g);
    }

    private void changePositionIcons(ObjectIcon objectIcon){
        setIcon(objectIcon.getNormalIcon());
        setPressedIcon(objectIcon.getPressedIcon());
        setRolloverIcon(objectIcon.getRolloverIcon());
    }

    @Override
    public String toString() {
        return "Position(x:" + xLabel + ", y:" + yLabel + ")";
    }
}
