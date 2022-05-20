package com.example.millgame;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.exceptions.RankedException;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class GameGUIMinimal extends JFrame {
    /*
     * Minimal GUI version of game (only GamePanel)
     */

    private BoardPanel boardPanel;

    public GameGUIMinimal(MillGame.GameVariant variant,
                          MillGame.GameMode mode) throws RankedException { // NOTE: handle this exception (ASAP)
        super();
        frameConfig();

        MillGame game = new MillGameBuilder().build(variant, mode);

        boardPanel = game.getBoardPanel();
        boardPanel.setBackground(new Color(128, 64, 32));

        Container mainPanel = getContentPane();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public GameGUIMinimal(MillGame game){
        super();
        frameConfig();

        boardPanel = game.getBoardPanel();
        boardPanel.setBackground(new Color(128, 64, 32));

        Container mainPanel = getContentPane();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void frameConfig(){
        TraceLogger.log(Level.INFO, "Initializing GameGUIMinimal");
        setTitle(Constants.title);
        Container mainPanel = getContentPane();
        mainPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}