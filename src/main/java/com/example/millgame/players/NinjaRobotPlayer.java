package com.example.millgame.players;

import com.example.millgame.MillGame;
import com.example.millgame.misc.Color;
import com.example.millgame.strategy.GameAnalysis;

public class NinjaRobotPlayer extends RobotPlayer {
    private GameAnalysis gameAnalysis;

    public NinjaRobotPlayer(Color color, MillGame game){super(color, game, RobotLevel.NINJA);}

    @Override
    public void autoPlacePiece(){
        try {
            gameAnalysis = new GameAnalysis(super.game);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // CODIGO PARA COLOCAR PIEZA (+ PENSADO)
    }

    @Override
    public void autoMovePiece(){
        // CODIGO PARA MOVER PIEZA (+ PENSADO)
    }

    @Override
    public void autoRemovePiece(){
        // CODIGO PARA REMOVER PIEZA (+ PENSADO)
    }

    private void GameAnalizer() {

    }

}
