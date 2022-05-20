package com.example.millgame.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private JButton start;
    private JLabel message;

    public ConfigPanel(){
        super();
        message = new JLabel("CONFIG PANEL");
        add(message);

        start = new JButton("Start");
        add(start);
    }

    public void addActionStartButton(AbstractAction action){
        start.addActionListener(action);
    }

    public BuildMillGameAction getBuildMillGameAction(){
        return new BuildMillGameAction();
    }

    public class BuildMillGameAction extends AbstractAction {
        public BuildMillGameAction(){
            super();
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            System.out.println("Building millgame Game");
        }
    }
}
