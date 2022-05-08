package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.pieces.PieceColor;
import com.example.millgame.Player;

public interface PlayerFactory {
    public Player create(PieceColor color, MillGame game);
    public Player createByLevel(PieceColor color, MillGame game, PlayerLevel level);
}
