package com.example.millgame;

import java.awt.CardLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLayout extends CardLayout{
    private JPanel controlPanel;

    public void setControlPanel(JPanel panel){ controlPanel = panel; }
    public GameLayoutAction getGameLayoutAction(){
        return new GameLayoutAction();
    }

    public class GameLayoutAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            next(controlPanel);
        }
    }
}
