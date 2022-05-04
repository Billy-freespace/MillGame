package com.example.millgame.panels;

import com.example.millgame.GameLayout;
import com.example.millgame.gameObjects.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {

    private final JLabel message;

    public ConfigPanel(){
        super();
        message = new JLabel(Constants.title);
        add(message);
    }

    public void addActionStartButton(AbstractAction action){

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
