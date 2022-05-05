package com.example.millgame.panels;

import com.example.millgame.gameObjects.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{
    private final JLabel title;
    private JButton init;

    public WelcomePanel(){
        super();
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);

        title = new JLabel("WELCOME PANEL");
        add(title);

        init = new JButton("Init");
        this.add(init);
    }

    public void addActionInitButton(AbstractAction action){
        init.addActionListener(action);
    }
}
