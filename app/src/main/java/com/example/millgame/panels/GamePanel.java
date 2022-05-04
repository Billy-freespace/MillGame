package com.example.millgame.panels;

import com.example.millgame.gameObjects.Constants;

import javax.swing.JPanel;
import java.awt.*;
import com.example.millgame.MillGame;

import javax.swing.*;

public class GamePanel extends JPanel {
    private JPanel msg;
    private MillGame game;

    public GamePanel() {
        super();
        add(msg);
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);
    }

    public void addActionResetButton(AbstractAction action){

    }
}
