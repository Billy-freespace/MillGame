package com.example.millgame;


import java.util.ArrayList;
import java.util.List;

enum PlayerType {
    HUMAN,
    ROBOT
}

enum RobotPlayerLevel {
    EASY,
    MEDIUM
}

public abstract class Player {
    private List<Piece> pieces;
    private int npieces;
    private PieceColor pieceColor;
    private Board board;

    Player(PieceColor color, Board board){
        this.board = board;
        GameVariant variant = board.getGameVariant();
        npieces = Board.getNumberPieces(variant);
        pieceColor = color;
        pieces = new ArrayList<Piece>(); // no pieces were placed to board
    }
    public void placePiece(char x, char y){
        Piece piece = new Piece(pieceColor);
        board.placePiece(piece, x, y);
    }
    public void movePiece(Piece piece, char x, char y){
        Position position = piece.getPosition();
        board.removePiece(position);
        board.placePiece(piece, x, y);
    }
    public void removePiece(Piece piece){
        Position position = piece.getPosition();
        board.removePiece(position);
        pieces.remove(piece);
    }
    public List<Mill> getMills(){return null;}
    public Piece getPiece(char x, char y){
        Position position = board.getPosition(x, y);
        Piece piece;
        if(position == null)
            piece = null;
        else
            piece = position.getPiece();

        return piece;
    }
}


class HumanPlayer extends Player {
    HumanPlayer(PieceColor color, Board board) {
        super(color, board);
    }
}

class RobotPlayer extends Player {
    private RobotPlayerLevel level;

    RobotPlayer(PieceColor color, Board board)
    {
        super(color, board);
        level = RobotPlayerLevel.EASY;
    }
    RobotPlayer(PieceColor color, Board board, RobotPlayerLevel level)
    {
        super(color, board);
        this.level = level;
    }
}
