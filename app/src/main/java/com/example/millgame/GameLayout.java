package com.example.millgame;

import java.awt.CardLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLayout extends CardLayout{
    private JPanel controlPanel;

    GameLayout(){
        super();
    }

    public void setControlPanel(JPanel panel){ controlPanel = panel; }
    public GameLayoutAction getGameLayoutAction(){
        return null;
    }

    public class GameLayoutAction extends AbstractAction {

        GameLayoutAction(JPanel controlPanel){
            super();
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            next(controlPanel);
        }
    }
}
