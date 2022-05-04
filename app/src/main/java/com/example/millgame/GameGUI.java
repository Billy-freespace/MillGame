package com.example.millgame;

import com.example.millgame.panels.ConfigPanel;
import com.example.millgame.panels.GamePanel;
import com.example.millgame.panels.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class MillGameGUI extends JFrame {
    private MillGame game;

    public MillGameGUI(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameLayout gameLayout = new GameLayout();

        JPanel controlPanel = new JPanel(gameLayout);
        gameLayout.setControlPanel(controlPanel);

        JPanel welcomePanel = new WelcomePanel(gameLayout.getGameLayoutAction());
        JPanel configPanel = new ConfigPanel(gameLayout.getGameLayoutAction());
        JPanel gamePanel = new GamePanel(gameLayout.getGameLayoutAction());

        game = null; // MillGame will be created at configuration panel (configPanel)
    }
}
