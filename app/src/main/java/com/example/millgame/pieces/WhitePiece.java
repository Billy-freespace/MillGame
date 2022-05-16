package com.example.millgame.pieces;

import com.example.millgame.Piece;

import javax.swing.*;

class WhitePiece extends Piece {
    public static final ImageIcon NORMAL_ICON = new ImageIcon("src/main/resources/textures/coin-white-normal.png");
    public static final ImageIcon PRESSED_ICON = new ImageIcon("src/main/resources/textures/coin-white-pressed.png");
    public static final ImageIcon ROLLOVER_ICON = new ImageIcon("src/main/resources/textures/coin-white-hover.png");

    public WhitePiece(){
        super(PieceColor.WHITE, NORMAL_ICON);
    }

    public ImageIcon getNormalIcon(){ return NORMAL_ICON; }
    public ImageIcon getPressedIcon(){ return PRESSED_ICON; }

    public ImageIcon getRolloverIcon(){ return ROLLOVER_ICON; }
}
