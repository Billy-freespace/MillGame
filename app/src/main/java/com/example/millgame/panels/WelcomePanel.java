package com.example.millgame.panels;

import com.example.millgame.gameObjects.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{
    private final JLabel title;
    private final JButton start;

    public WelcomePanel(){
        super();
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);

        title = new JLabel("WELCOME PANEL");
        add(title);

        Icon btnIcon = new ImageIcon("app/src/main/resources/textures/nmm_button-normal.png");
        start = new JButton(btnIcon);
        this.add(start);
    }

    public void addActionInitButton(AbstractAction action){
        start.addActionListener(action);
    }
}
