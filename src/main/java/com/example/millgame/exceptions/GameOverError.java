package com.example.millgame.exceptions;

import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.actions.EventAction;

import java.util.logging.Level;

public class GameOverError extends RankedException {
    public GameOverError(Player player, Position position, Class eventAction){
        super(getErrorMessage(player, position, eventAction));
    }

    public GameOverError(Player player, Position position, Class eventAction, Level rank){
        super(getErrorMessage(player, position, eventAction), rank);
    }

    public static String getErrorMessage(Player player, Position position, Class eventAction){
        String error = "Game is over!";
        error += " Invalid play: player="+ player + ", position=" + position + ", event=" + eventAction;

        return error;
    }
}
