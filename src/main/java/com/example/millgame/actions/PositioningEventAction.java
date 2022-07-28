package com.example.millgame.actions;

import com.example.millgame.Board;
import com.example.millgame.Player;
import com.example.millgame.Position;
import com.example.millgame.exceptions.*;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.players.PlayerType;
import com.example.millgame.players.RobotPlayer;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;

public class PositioningEventAction extends EventAction {

  public PositioningEventAction(){super(ActionType.POSITIONING);}
  @Override
  public void actionPerformed(ActionEvent event) {
    Position position = (Position) event.getSource();

    //game.getBoard().listPositions();
    TraceLogger.log(Level.INFO, position + " was selected", PositioningEventAction.class);
    //System.out.println("POSITION: " + position);

    try{
      Player player = game.getActivePlayer();

      if(game.isGameOver()){
        throw new GameOverError(player, position, MovingEventAction.class);
      }

      player.placePiece(position);
      if (player.getType() == PlayerType.ROBOT)
        TraceLogger.log(Level.INFO, "Placing piece - player type is robot");
      else
        TraceLogger.log(Level.INFO, "Placing piece - player is not robot");

      // CHECK IF A MILL WAS FORMED
      List<Board.Mill> mills = game.getMills(position.getPiece());
      if(mills.size() > 0){
        // HIGHLIGHT POSSIBLE POSITIONS TO DELETE
        TraceLogger.log(Level.INFO, "mills were formed: " + mills);
        game.changeEventAction(new RemovingEventAction());
        game.getBoardPanel().repaint();
        if (player.getType() == PlayerType.ROBOT){
          TraceLogger.log(Level.INFO, "Robot player -> remove piece");
          game.notifyTurnPlayer();
          game.nextTurn();
        } else {
          TraceLogger.log(Level.INFO, "player type is not robot");
        }
      } else {
        Player opponent = game.getOpponentPlayer();
        int gamePieces = game.getNumberPlayerPieces();

        if(player.getPlacedPieces() == gamePieces &&
            opponent.getPlacedPieces() == gamePieces){
          // both players have positioned all their pieces, so change event action to Moving
          // for the next play
          game.changeEventAction(new MovingEventAction());
        }
        game.nextTurn();
      }

    } catch (InvalidPositionCoordinate | NotEmptyPosition | GameOverError error) {
      TraceLogger.log(error, PositioningEventAction.class);
    } catch (NoPiecesError error){
      // the player was positioned all their pieces, so now it will move them
      TraceLogger.log(error, PositioningEventAction.class);
      game.changeEventAction(new MovingEventAction());
    } catch (RankedException error){
      TraceLogger.log(error);
    }
  }
}
