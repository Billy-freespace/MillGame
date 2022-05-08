package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.Player;

import com.example.millgame.pieces.PieceColor;

public class RobotPlayerFactory implements PlayerFactory{
    public Player create(PieceColor color, MillGame game){return null;}
    public Player createByLevel(PieceColor color, MillGame game, PlayerLevel level){return null;}
}
