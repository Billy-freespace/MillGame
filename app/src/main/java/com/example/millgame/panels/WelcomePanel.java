package com.example.millgame.panels;

import com.example.millgame.gameObjects.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel{
    private final JLabel title;
    private JButton simpleButton;

    public WelcomePanel(){
        super();
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setFocusable(true);

        title = new JLabel(Constants.title);
        add(title);

        // button added only for testing purposes (DELETE)
        simpleButton = new JButton("Simple Button (TESTING)");
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pressed button!");
            }
        });

        this.add(simpleButton);
    }

    public void addActionInitButton(AbstractAction action){

    }
}
