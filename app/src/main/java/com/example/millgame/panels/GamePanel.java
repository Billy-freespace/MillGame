package com.example.millgame.panels;

import com.example.millgame.boards.BoardPanel;
import com.example.millgame.MillGame;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GamePanel extends JPanel {
    private MillGame game;
    public JPanel logPanel;
    private JButton reset;
    private JButton quit;
    private static final Insets insets = new Insets(0,0,0,0);
//    private JLabel message; // this label is only for testing purpose (DELETE)

    public GamePanel(MillGame game) {
        super();

//        message = new JLabel("GAME PANEL");
//        add(message);

//
        Icon btnIconNormal = new ImageIcon("app/src/main/resources/textures/nmm_button-normal.png");
        Icon btnIconHover = new ImageIcon("app/src/main/resources/textures/nmm_button-hover.png");
        Icon btnIconPressed = new ImageIcon("app/src/main/resources/textures/nmm_button-pressed.png");
        reset = new JButton("<html><h1 style='color: white;'>Reset</h1>", btnIconNormal);
        reset.setHorizontalTextPosition(JButton.CENTER);
        reset.setVerticalTextPosition(JButton.CENTER);
        reset.setPreferredSize(new Dimension(120, 60));
//        reset.setRolloverIcon(btnIconHover);
//        reset.setPressedIcon(btnIconPressed);
//        reset.setFocusPainted(false);
//        reset.setBorderPainted(false);
//        reset.setContentAreaFilled(false);

        quit = new JButton("<html><h1 style='color: white;'>Quit</h1>", btnIconNormal);
        quit.setHorizontalTextPosition(JButton.CENTER);
        quit.setVerticalTextPosition(JButton.CENTER);
        quit.setPreferredSize(new Dimension(120, 60));

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