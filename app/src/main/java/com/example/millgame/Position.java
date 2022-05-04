package com.example.millgame;

import javax.swing.JButton;
import java.awt.Point;
import java.util.ArrayList;

public class Position extends JButton {
    private char xLabel;
    private int yLabel;
    private Piece piece;
    private ArrayList<Position> neighbours;
    public boolean mark = false;

    public Position(char xLabel, int yLabel) {
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        neighbours = new ArrayList<Position>();
        piece = null;
        mark = false;
    }

    public Position(Point point, char xLabel, int yLabel){}
    public void addNeighbour(Position position){
        neighbours.add(position);
        position.addNeighbour(this);
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
    public void setPoint(Point point){}
    public void setMark(boolean value){}

    public char getXLabel(){ return xLabel; }
    public int getYLabel(){ return yLabel; }
}
