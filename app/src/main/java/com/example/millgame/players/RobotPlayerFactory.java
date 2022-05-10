package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.pieces.PieceColor;

public class RobotPlayerFactory extends PlayerFactory{
    public Player create(PieceColor color, MillGame game){ return new RobotPlayer(color, game); }
    public Player create(PieceColor color, Board board) { return new RobotPlayer(color, board); }
    public Player createByLevel(PieceColor color, MillGame game, PlayerLevel level) { return null; }
    public Player createByLevel(PieceColor color, Board board, PlayerLevel level) { return null; }
}
