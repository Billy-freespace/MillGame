package com.example.millgame;

import com.example.millgame.panels.ConfigPanel;
import com.example.millgame.panels.GamePanel;
import com.example.millgame.panels.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private JPanel controlPanel;
    private GameLayout gameLayout;

    public GameGUI(){
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameLayout = new GameLayout();

        controlPanel = new JPanel(gameLayout);
        gameLayout.setControlPanel(controlPanel);

        JPanel welcomePanel = new WelcomePanel();
        JPanel configPanel = new ConfigPanel();
        JPanel gamePanel = new GamePanel();

        controlPanel.add(welcomePanel);
        controlPanel.add(configPanel);
        controlPanel.add(gamePanel);

        GameLayout.GameLayoutAction gameLayoutAction = gameLayout.getGameLayoutAction();
        // ADD gameLayoutAction AbstractAction to each panel of controlPanel (welcomePanel, configPanel, gamePanel)

        Container mainPanel = this.getContentPane();
        mainPanel.add(configPanel, BorderLayout.CENTER);
    }
}
