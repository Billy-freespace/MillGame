package com.example.millgame.panels;

<<<<<<< HEAD
import com.example.millgame.Board;
import com.example.millgame.gameObjects.Constants;

import javax.swing.JPanel;
import java.awt.*;
=======
import com.example.millgame.GameLayout;
import com.example.millgame.MillGame;

import javax.swing.*;
>>>>>>> c5ea83de31d73ffb6c741cdf12e79134b75f6ad0

public class GamePanel extends JPanel {
    private JPanel msg;
    private MillGame game;

<<<<<<< HEAD
    GamePanel(Board board){
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);
=======
    public GamePanel(){
        super();
        add(msg);
    }

    public void addActionResetButton(AbstractAction action){

>>>>>>> c5ea83de31d73ffb6c741cdf12e79134b75f6ad0
    }
}
