package com.example.millgame.panels;

import com.example.millgame.Board;
import com.example.millgame.boards.BoardPanel;
import com.example.millgame.gameObjects.Constants;
import com.example.millgame.MillGame;
import com.example.millgame.graphicsAndSounds.Assets;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private MillGame game;
    private JButton reset;
    private JLabel message; // this label is only for testing purpose (DELETE)

    public GamePanel(MillGame game) {
        super();

        message = new JLabel("GAME PANEL");
        add(message);

        reset = new JButton("reset");
        add(reset, BorderLayout.SOUTH);

        /*
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);
         */

        BoardPanel boardPanel = game.getBoardPanel();
        add(boardPanel);
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