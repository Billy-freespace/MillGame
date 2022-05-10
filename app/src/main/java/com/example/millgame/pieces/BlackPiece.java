package com.example.millgame.pieces;

import com.example.millgame.ObjectIcon;
import com.example.millgame.Piece;

import javax.swing.*;

class BlackPiece extends Piece{

    public static final ImageIcon NORMAL_ICON = new ImageIcon("src/main/resources/textures/nmm_coin-black-normal.png");
    public static final ImageIcon PRESSED_ICON = new ImageIcon("src/main/resources/textures/nmm_coin-black-pressed.png");
    public static final ImageIcon ROLLOVER_ICON = new ImageIcon("src/main/resources/textures/nmm_coin-black-hover.png");

    public BlackPiece(){
        super(PieceColor.BLACK, NORMAL_ICON);
    }

    public ImageIcon getNormalIcon(){ return NORMAL_ICON; }
    public ImageIcon getPressedIcon(){ return PRESSED_ICON; }
    public ImageIcon getRolloverIcon(){ return ROLLOVER_ICON; }
}