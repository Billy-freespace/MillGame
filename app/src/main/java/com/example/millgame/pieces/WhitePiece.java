package com.example.millgame.pieces;

import com.example.millgame.Piece;

import javax.swing.*;

class WhitePiece extends Piece {
    public static final ImageIcon DEFAULT_ICON = new ImageIcon("src/main/resources/textures/nmm_coin-white-normal.png");
    public WhitePiece(){
        super(PieceColor.WHITE, DEFAULT_ICON);
    }
}
