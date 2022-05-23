package com.example.millgame;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Constants;
import com.example.millgame.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class GameGUIMinimal extends JFrame {
    /*
     * Minimal GUI version of game (only GamePanel)
     */

    private GamePanel gamePanel;
    private BoardPanel boardPanel;

    private Color defaultColor = new Color(128, 64, 32);

    public GameGUIMinimal(MillGame game){
        super();
        //TraceLogger.log(Level.INFO, "Initializing GameGUIMinimal");
        setTitle(Constants.title);
        Container mainPanel = getContentPane();
        mainPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        boardPanel = game.getBoardPanel();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
    }


    public GameGUIMinimal setBoardBackground(Color color){
        //gamePanel.setBackground(color);
        boardPanel.setBackground(color);

        return this;
    }
}