package com.example.millgame;

import javax.swing.JButton;
import java.util.ArrayList;

public class Position extends JButton {
    private char xLabel;
    private int yLabel;
    private Piece piece;
    //private Position[] neighbours;
    private ArrayList<Position> neighbours;
    public boolean mark = false;

    public Position(char xLabel, int yLabel) {
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        neighbours = new ArrayList<Position>();
        piece = null;
        mark = false;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece() {
        return piece;
    }
    public ArrayList<Position> getNeighbours(){
        return neighbours;
    }

    public void addNeighbour(Position position){
        neighbours.add(position);
        position.addNeighbour(this);
    }
}
