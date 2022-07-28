package com.example.millgame.actions;

import com.example.millgame.*;
import com.example.millgame.exceptions.GameOverError;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;

import java.awt.event.ActionEvent;
import java.util.logging.Level;

public class RemovingEventAction extends EventAction {

  public RemovingEventAction(){super(ActionType.REMOVING);}
  @Override
  public void actionPerformed(ActionEvent event){
    Position position = (Position) event.getSource();

    // REMOVE THIS CODE - ADDED FOR TESTING PURPOSES
    // BEGIN
    TraceLogger.log(Level.INFO, position + " was selected", RemovingEventAction.class);
    // END

    try {
      Player opponent = game.getOpponentPlayer();

      if(game.isGameOver()){
        Player player = game.getActivePlayer();
        throw new GameOverError(player, position, MovingEventAction.class);
      }

      game.removeOpponentPiece(position.getPiece());

      EventAction eventAction;
      if(opponent.getPlacedPieces() < game.getNumberPlayerPieces()){
        eventAction = new PositioningEventAction();
      } else { // all pieces were already placed
        eventAction = new MovingEventAction();
      }

      game.changeEventAction(eventAction);
      game.nextTurn();

    } catch (GameOverError error){
      TraceLogger.log(error, RemovingEventAction.class);
    } catch (Exception error){
      RankedException exception = new RankedException(error, Level.WARNING);
      TraceLogger.log(exception, RemovingEventAction.class);
    }
  }
}