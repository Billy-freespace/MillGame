package com.example.millgame.panels;

import com.example.millgame.Board;
import com.example.millgame.gameObjects.Constants;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{

    GamePanel(Board board){
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);
    }
}
