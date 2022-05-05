package com.example.millgame.panels;

import com.example.millgame.gameObjects.Constants;

import javax.swing.JPanel;
import java.awt.*;

import com.example.millgame.MillGame;
import com.example.millgame.graphicsAndSounds.Assets;

import javax.swing.*;

public class GamePanel extends JPanel {
    private MillGame game;
//    private final JLabel title;

    public GamePanel() {
        super();

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);

        JPanel msg = new MyPanel();
//        title = new JLabel(Constants.title);
        add(msg);
//        add(title);
    }

    public void addActionResetButton(AbstractAction action){

    }
}

class MyPanel extends JPanel {

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Text
        g.drawImage(Assets.background, 0, 0, null);
//        g.drawString("This is my custom Panel!",10,20);
    }
}