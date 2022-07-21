package com.example.millgame.strategy;

import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.Player;

import java.util.List;

public class SimulatePossibleMill {
    private MillGame millGameClone;
    private List<Piece> Pieces;

    public SimulatePossibleMill(MillGame game) throws CloneNotSupportedException {
        millGameClone = game.clone();
    }

    public void SimulateOneStep() {
        Player player = millGameClone.getActivePlayer();
        Pieces = player.getBoardPieces();

    }
}
