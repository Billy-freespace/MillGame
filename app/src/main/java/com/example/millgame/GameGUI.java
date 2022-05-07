package com.example.millgame;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.gameObjects.Constants;
import com.example.millgame.logging.GameLogger;
import com.example.millgame.panels.*;
import com.example.millgame.pieces.PieceColor;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private JPanel controlPanel;
    private GameLayout gameLayout;

    private GameLogger logger;

    public GameGUI(){
        super();
        logger = GameLogger.getLogger(this.getName());
        logger.info("Initializing MillGame GUI");
        MillGame game = new MillGameBuilder().build(MillGame.GameVariant.NINE_MEN_MORRIS);

        setTitle(Constants.title);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);

        gameLayout = new GameLayout();
        controlPanel = new JPanel(gameLayout);

//        controlPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        WelcomePanel welcomePanel = new WelcomePanel();
        ConfigPanel configPanel = new ConfigPanel();
        //GamePanel gamePanel = new GamePanel(game);

        // REMOTE THIS STUFF - ONLY FOR TESTING PURPOSE
        // BEGIN

        BoardPanel boardPanel = game.getBoardPanel();
        //boardPanel.add(new JLabel("GAME BOARD"));
        boardPanel.setBackground(new Color(128, 128, 128));

        //Position position = new Position('a', 1);

        //boardPanel.add(position);

        //JButton button = new JButton(Position.NORMAL_ICON);
        //board.add(button);

        // END

        controlPanel.add(boardPanel);
        controlPanel.add(welcomePanel);
        controlPanel.add(configPanel);
        //controlPanel.add(gamePanel);

        gameLayout.setControlPanel(controlPanel);
        GameLayout.GameLayoutAction gameLayoutAction = gameLayout.getGameLayoutAction();

        welcomePanel.addActionInitButton(gameLayoutAction);

        configPanel.addActionStartButton(gameLayoutAction);
        configPanel.addActionStartButton(configPanel.getBuildMillGameAction());

        //gamePanel.addActionResetButton(gameLayoutAction);

        Container mainPanel = this.getContentPane();
        mainPanel.add(controlPanel, BorderLayout.CENTER);
    }
}
