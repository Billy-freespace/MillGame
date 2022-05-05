package com.example.millgame;

import com.example.millgame.gameObjects.Constants;
import com.example.millgame.panels.*;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private JPanel controlPanel;
    private GameLayout gameLayout;

    public GameGUI(){
        super();

        setTitle(Constants.title);
        //setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        //setLocationRelativeTo(null);

        gameLayout = new GameLayout();
        controlPanel = new JPanel(gameLayout);

        WelcomePanel welcomePanel = new WelcomePanel();
        ConfigPanel configPanel = new ConfigPanel();
        GamePanel gamePanel = new GamePanel();

        controlPanel.add(gamePanel);
        controlPanel.add(welcomePanel);
        controlPanel.add(configPanel);

        gameLayout.setControlPanel(controlPanel);
        GameLayout.GameLayoutAction gameLayoutAction = gameLayout.getGameLayoutAction();

        // ADD gameLayoutAction AbstractAction to each panel of controlPanel (welcomePanel, configPanel, gamePanel)
        welcomePanel.addActionInitButton(gameLayoutAction);

        configPanel.addActionStartButton(gameLayoutAction);
        configPanel.addActionStartButton(configPanel.getBuildMillGameAction());

        gamePanel.addActionResetButton(gameLayoutAction);

        Container mainPanel = this.getContentPane();
        mainPanel.add(controlPanel, BorderLayout.CENTER);
    }
}
