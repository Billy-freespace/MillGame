package com.example.millgame;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.gameObjects.Constants;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class GameGUI extends JFrame {
    private JPanel controlPanel;
    private GameLayout gameLayout;

    private TraceLogger traceLogger;

    public GameGUI(){
        super();
        TraceLogger.log(Level.INFO, "Initializing GameGUI");

        // NOTE: This game will be initialized in ConfigPanel
        MillGame game = new MillGameBuilder().build(MillGame.GameVariant.NINE_MEN_MORRIS, MillGame.GameMode.HUMAN_HUMAN);
        game.nextTurn(); // init game turn

        setTitle(Constants.title);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);

        gameLayout = new GameLayout();
        controlPanel = new JPanel(gameLayout);

//        controlPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        WelcomePanel welcomePanel = new WelcomePanel();
        TraceLogger.log(Level.FINE, "Initializing WelcomePanel");

        ConfigPanel configPanel = new ConfigPanel();
        TraceLogger.log(Level.FINE, "Initializing ConfigPanel");
        GamePanel gamePanel = new GamePanel(game);

        // REMOTE THIS STUFF - ONLY FOR TESTING PURPOSE
        // BEGIN

        BoardPanel boardPanel = game.getBoardPanel();
        //boardPanel.add(new JLabel("GAME BOARD"));
        boardPanel.setBackground(new Color(128, 128, 128));

        //Position position = new Position('a', 1);

        //boardPanel.add(position);

        //JButton button = new JButton(Position.NORMAL_ICON);
        //board.add(button);
        controlPanel.add(boardPanel);
        // END

        controlPanel.add(welcomePanel);
        controlPanel.add(configPanel);
        controlPanel.add(gamePanel);

        gameLayout.setControlPanel(controlPanel);
        GameLayout.GameLayoutAction gameLayoutAction = gameLayout.getGameLayoutAction();

        welcomePanel.addActionInitButton(gameLayoutAction);

        configPanel.addActionStartButton(gameLayoutAction);
        configPanel.addActionStartButton(configPanel.getBuildMillGameAction());

        //gamePanel.addActionResetButton(gameLayoutAction);

        Container mainPanel = this.getContentPane();
        TraceLogger.log(Level.FINE, "Adding controlPanel in Frame Panel");
        mainPanel.add(controlPanel, BorderLayout.CENTER);
    }

    public void setTraceLogger(TraceLogger traceLogger){ this.traceLogger = traceLogger; }
}
