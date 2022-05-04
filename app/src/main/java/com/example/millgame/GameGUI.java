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

        WelcomePanel welcomePanel = new WelcomePanel();
        ConfigPanel configPanel = new ConfigPanel();
        GamePanel gamePanel = new GamePanel();

        controlPanel.add(welcomePanel);
        controlPanel.add(configPanel);
        controlPanel.add(gamePanel);

        gameLayout.setControlPanel(controlPanel);
        GameLayout.GameLayoutAction gameLayoutAction = gameLayout.getGameLayoutAction();

        // ADD gameLayoutAction AbstractAction to each panel of controlPanel (welcomePanel, configPanel, gamePanel)
        welcomePanel.addActionInitButton(gameLayoutAction);

        configPanel.addActionStartButton(gameLayoutAction);
        configPanel.addActionStartButton(configPanel.getBuildMillGameAction());

        gamePanel.addActionResetButton(gameLayoutAction);

        Container mainPanel = this.getContentPane();
        mainPanel.add(configPanel, BorderLayout.CENTER);
    }
}
