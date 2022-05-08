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

        MillGame game = new MillGameBuilder().build(MillGame.GameVariant.NINE_MEN_MORRIS);

        setTitle(Constants.title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        gameLayout = new GameLayout();
        controlPanel = new JPanel(gameLayout);

//        controlPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        WelcomePanel welcomePanel = new WelcomePanel();
        ConfigPanel configPanel = new ConfigPanel();
        GamePanel gamePanel = new GamePanel(game);

        controlPanel.add(welcomePanel);
        controlPanel.add(configPanel);
        controlPanel.add(gamePanel);

        gameLayout.setControlPanel(controlPanel);
        GameLayout.GameLayoutAction gameLayoutAction = gameLayout.getGameLayoutAction();

        welcomePanel.addActionInitButton(gameLayoutAction);

        configPanel.addActionStartButton(gameLayoutAction);
        configPanel.addActionStartButton(configPanel.getBuildMillGameAction());

        gamePanel.addActionResetButton(gameLayoutAction);

        Container mainPanel = this.getContentPane();
        mainPanel.add(controlPanel);
    }
}
