package com.example.millgame.players;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.misc.Color;

public class RobotPlayerFactory extends PlayerFactory{
    public Player create(Color color, MillGame game){ return new RobotPlayer(color, game); }
    public Player create(Color color, Board board) { return new RobotPlayer(color, board); }
    public Player createByLevel(Color color, MillGame game, PlayerLevel level) { return null; }
    public Player createByLevel(Color color, Board board, PlayerLevel level) { return null; }
}
