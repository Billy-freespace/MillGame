package com.example.millgame.strategy;

import java.util.HashMap;
import java.util.List;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.PieceRadar;
import com.example.millgame.Position;

public class GameAnalysis {
<<<<<<< HEAD
    private MillGame game;
    private HashMap<Piece, List <Position>> opponentPosibleMills;
=======
    private HashMap<Piece, List<Position>> opponentPosibleMills;
>>>>>>> 48717b31c8c75faae684da7b479189e695978ace
    private HashMap<Piece, List <Position>> ownPosibleMills;
    private List<GameStep> playbooks;
    private int MAX_NUM_STEPS;

    private PieceRadar radar;
    private MillGame millGameClone;

    public GameAnalysis(MillGame game) throws CloneNotSupportedException {
        millGameClone = game.clone();
    }

    private void init() {
        ;
    }
    
    private void findPossibleSteps() {
        ;
    }

    private MillGame applyGameStep(GameStep step) {
        ;
    }

    public void apply() {
        ;
    }

}