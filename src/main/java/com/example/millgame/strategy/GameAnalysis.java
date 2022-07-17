package com.example.millgame.strategy;

import java.util.HashMap;

import com.example.millgame.MillGame;
import com.example.millgame.Position;

public class GameAnalysis {
    private HashMap<Piece, List <Position>> opponentPosibleMills;
    private HashMap<Piece, List <Position>> ownPosibleMills;
    private PieceRadar radar;
    private MillGame millGameClone;

    public GameAnalysis(MillGame game) throws CloneNotSupportedException {
        millGameClone = game.clone();
    }

    

}