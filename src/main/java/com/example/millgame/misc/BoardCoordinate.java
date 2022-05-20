package com.example.millgame.misc;

public class BoardCoordinate {
    private char x;
    private int y;
    public BoardCoordinate(char x, int y){
        this.x = x;
        this.y = y;
    }

    public char getX(){ return x;}
    public int getY(){ return y;}
}
