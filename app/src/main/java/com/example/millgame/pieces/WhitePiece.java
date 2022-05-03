package com.example.millgame.pieces;

import com.example.millgame.Piece;
import com.example.millgame.Position;

import javax.swing.*;

class WhitePiece extends Piece {
    public static Icon pieceIcon = new ImageIcon("resources/SOMTHING-WHITE.jpg");
    public static  Icon selectedPieceIcon = new ImageIcon("resources/SOMTHING-WHITE.jpg");
    public WhitePiece(){
        super(PieceColor.WHITE);
    }
    public WhitePiece(Position position){
        super(PieceColor.WHITE, position);
    }
}
