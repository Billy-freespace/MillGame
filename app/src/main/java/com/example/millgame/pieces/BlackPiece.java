package com.example.millgame.pieces;

import com.example.millgame.Piece;
import com.example.millgame.Position;

import javax.swing.*;

class BlackPiece extends Piece {

    public static Icon pieceIcon = new ImageIcon("resources/SOMTHING-BLACK.jpg");
    public static  Icon selectedPieceIcon = new ImageIcon("resources/SOMTHING-BLACK.jpg");
    public BlackPiece(){
        super(PieceColor.BLACK);
    }
    public BlackPiece(Position position){
        super(PieceColor.BLACK, position);
    }
}