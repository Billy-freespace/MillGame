package com.example.millgame.panels;

import com.example.millgame.GameLayout;
import com.example.millgame.gameObjects.Constants;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel{
    private final JLabel title;

    public WelcomePanel(){
        super();
        title = new JLabel(Constants.title);
        add(title);
    }

    public void addActionInitButton(AbstractAction action){

    }
}
