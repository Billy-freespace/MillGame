package com.example.millgame.pieces;

import com.example.millgame.Piece;
import com.example.millgame.misc.Color;

import javax.swing.*;

class BlackPiece extends Piece{

    public static final ImageIcon NORMAL_ICON = new ImageIcon("src/main/resources/textures/coin-black-normal.png");
    public static final ImageIcon PRESSED_ICON = new ImageIcon("src/main/resources/textures/coin-black-pressed.png");
    public static final ImageIcon ROLLOVER_ICON = new ImageIcon("src/main/resources/textures/coin-black-hover.png");

    public BlackPiece(){
        super(Color.BLACK, NORMAL_ICON);
    }

    public ImageIcon getPressedIcon(){ return PRESSED_ICON; }
    public ImageIcon getRolloverIcon(){ return ROLLOVER_ICON; }
}