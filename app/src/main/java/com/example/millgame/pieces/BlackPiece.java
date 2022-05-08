package com.example.millgame.pieces;

import com.example.millgame.Piece;

import javax.swing.*;

class BlackPiece extends Piece {

    public static final ImageIcon DEFAULT_ICON = new ImageIcon("src/main/resources/textures/nmm_coin-black-normal.png");

    public BlackPiece(){
        super(PieceColor.BLACK, DEFAULT_ICON);
    }
}