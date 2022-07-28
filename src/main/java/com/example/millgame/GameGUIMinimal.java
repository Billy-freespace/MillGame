package com.example.millgame;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Constants;
import com.example.millgame.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public class GameGUIMinimal extends JFrame {
  /*
   * Minimal GUI version of game (only GamePanel)
   */

  private MillGame game;

  private GamePanel gamePanel;
  private BoardPanel boardPanel;

  private JLabel activeTurn;

  private Color defaultColor = new Color(128, 64, 32);

  public GameGUIMinimal(MillGame game){
    super();
    //TraceLogger.log(Level.INFO, "Initializing GameGUIMinimal");
    setTitle(Constants.title);
    Container contentPanel = getContentPane();
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

    this.game = game;
    boardPanel = game.getBoardPanel();
    boardPanel.setBackground(defaultColor);
    boardPanel.setVisible(true);

    activeTurn = new JLabel("Active Turn: ", game.getActivePlayer().getPieceIcon(), JLabel.CENTER);
    activeTurn.setHorizontalTextPosition(JLabel.LEFT);
    activeTurn.setBackground(new Color(96, 96, 96));
    activeTurn.setHorizontalAlignment(JLabel.CENTER);
    activeTurn.setVisible(true);

    ActionListener turnListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        Player activePlayer = (Player) actionEvent.getSource();

        TraceLogger.log(Level.INFO, "GameGUI.activeTurn.ActionListener (turn bar: JLabel): " + actionEvent);
        Icon activePlayerIcon = activePlayer.getPieceIcon();
        if(game.isGameOver()){
          activeTurn.setText("Winner: ");
          activePlayerIcon = game.getWinner().getPieceIcon();
        }

        activeTurn.setIcon(activePlayerIcon);
      }
    };

    game.addTurnListener(turnListener);

    contentPanel.add(boardPanel, BorderLayout.CENTER);
    contentPanel.add(activeTurn, BorderLayout.PAGE_END);
  }


  public GameGUIMinimal setBoardBackground(Color color){
    boardPanel.setBackground(color);

    return this;
  }
}