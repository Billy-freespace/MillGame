package com.example.millgame.panels;

import com.example.millgame.MillGame;
import com.example.millgame.boards.BoardPanel;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private MillGame game;
    private JButton reset;
    private JLabel turnLabel;
    private BoardPanel boardPanel;

    public GamePanel(MillGame game) {
        super();

        boardPanel = game.getBoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        turnLabel = new JLabel("Active Turn");
        //add(turnLabel, BorderLayout.SOUTH);

        reset = new JButton("reset");
        //add(reset, BorderLayout.NORTH);
    }

    public void setBoardBackground(Color color){
        boardPanel.setBackground(color);
    }

    /*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Assets.background, 0, 0, null);
    }
     */

    public void addActionResetButton(AbstractAction action){
        reset.addActionListener(action);
    }
}