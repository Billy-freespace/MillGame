package com.example.millgame;

import com.example.millgame.actions.EventAction;
import com.example.millgame.logging.TraceLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

interface PositionEventAction{
    public void setEventAction(EventAction eventAction);
}

public class Position extends JButton implements PositionEventAction, ObjectIcon {
    private static final ImageIcon NORMAL_ICON = new ImageIcon("src/main/resources/textures/point-normal.png");
    private static final ImageIcon PRESSED_ICON = new ImageIcon("src/main/resources/textures/point-pressed.png");
    private static final ImageIcon ROLLOVER_ICON = new ImageIcon("src/main/resources/textures/point-hover.png");

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

    public boolean hasPiece(){ return piece != null ; }

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
        ObjectIcon objectIcon = this;
        if(piece != null){
            objectIcon = piece;
        }

        changePositionIcons(objectIcon);
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
