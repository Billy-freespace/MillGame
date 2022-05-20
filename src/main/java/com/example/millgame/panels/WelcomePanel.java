package com.example.millgame.panels;

import com.example.millgame.graphicsAndSounds.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{
    private final JButton init;

    public WelcomePanel(){
        super();
        //setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
//        setOpaque(false);
        setFocusable(true);

        init = new JButton("Init");
        this.add(init);
    }

    public void addActionInitButton(AbstractAction action){
        init.addActionListener(action);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Assets.background, 0, 0, null);
    }
}
