package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.Piece.PieceColor;
import com.example.millgame.Player;
import com.example.millgame.Player.PlayerLevel;

public interface PlayerFactory {
    public Player create(PieceColor color, Board board);
    public Player createByLevel(PieceColor color, Board board, PlayerLevel level);
}
