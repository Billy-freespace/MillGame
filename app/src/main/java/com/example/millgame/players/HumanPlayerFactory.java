package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Piece;
import com.example.millgame.Player;

public class HumanPlayerFactory implements PlayerFactory{
    public Player create(Piece.PieceColor color, Board board){return null;}
    public Player createByLevel(Piece.PieceColor color, Board board, Player.PlayerLevel level){return null;}
}
