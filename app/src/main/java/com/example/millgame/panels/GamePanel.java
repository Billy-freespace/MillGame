package com.example.millgame.panels;

import com.example.millgame.Board;
import com.example.millgame.gameObjects.Constants;
import com.example.millgame.MillGame;
import com.example.millgame.graphicsAndSounds.Assets;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GamePanel extends JPanel {
    private MillGame game;
    private JButton reset;
    private JButton quit;
    private static final Insets insets = new Insets(0,0,0,0);
//    private JLabel message; // this label is only for testing purpose (DELETE)

    public GamePanel(MillGame game) {
        super();

//        message = new JLabel("GAME PANEL");
//        add(message);

//
        Icon btnIcon = new ImageIcon("app/src/main/resources/textures/nmm_button-normal.png");
        reset = new JButton(btnIcon);
        reset.setPreferredSize(new Dimension(120, 60));

        quit = new JButton(btnIcon);
        quit.setPreferredSize(new Dimension(120, 60));

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);

        setLayout(new GridBagLayout());

        Border blackBorder = BorderFactory.createLineBorder(Color.black);

        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(600, 20));
        panelTop.setBorder(blackBorder);
        panelTop.setOpaque(false);

        JPanel panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(100, 400));
        panelLeft.setBorder(blackBorder);
        panelLeft.setOpaque(false);

        JPanel panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(100, 400));
        panelRight.setBorder(blackBorder);
        panelRight.setOpaque(false);

        JPanel panelBottom = new JPanel();
        panelBottom.setPreferredSize(new Dimension(600, 180));
        panelBottom.setBorder(blackBorder);
        panelBottom.setOpaque(false);

        Board board = game.getBoard();
        board.setPreferredSize(new Dimension(400, 400));
        board.setBorder(blackBorder);
        board.setOpaque(false);

        addComponent(panelTop, 0, 0, 60, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panelLeft, 0, 2, 10, 40, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(board, 10, 2, 40, 40, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panelRight, 50, 2, 10, 40, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        addComponent(panelBottom, 0, 42, 60, 18, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        addComponent(reset, 50, 2, 12, 6, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        addComponent(quit, 50, 46, 12, 6, GridBagConstraints.CENTER, GridBagConstraints.NONE);

//        add(board);
//        add(reset, BorderLayout.SOUTH);
    }

    private void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0.0, 0.0, anchor, fill, insets, 0, 0);
        add(component, gbc);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Assets.background, 0, 0, null);
    }

    public void addActionResetButton(AbstractAction action){
        reset.addActionListener(action);
    }
}