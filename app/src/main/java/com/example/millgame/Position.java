package com.example.millgame;

import javax.swing.JButton;
import java.util.ArrayList;

public class Position extends JButton {
    private char x;
    private char y;
    private Piece piece;
    //private Position[] neighbours;
    private ArrayList<Position> neighbours;
    boolean mark = false;

    public Position(char x, char y) {
        this.x = x;
        this.y = y;
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
}
