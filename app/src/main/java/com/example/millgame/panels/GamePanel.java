package com.example.millgame.panels;

import com.example.millgame.gameObjects.Constants;

import javax.swing.JPanel;
import java.awt.*;

import com.example.millgame.MillGame;
import com.example.millgame.graphicsAndSounds.Assets;

import javax.swing.*;

public class GamePanel extends JPanel {
    private MillGame game;

    public GamePanel() {
        super();

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
//        setLayout(new GridBagLayout());
        setFocusable(true);

        BoardPanel board = new BoardPanel();
        add(board);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Assets.background, 0, 0, null);
    }

    public void addActionResetButton(AbstractAction action){

    }
}


class BoardPanel extends JPanel {

    public BoardPanel() {
        setOpaque(false);
//        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Assets.board, 0, 0, null);
    }

}