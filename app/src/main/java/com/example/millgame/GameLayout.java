package com.example.millgame;

import java.awt.CardLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLayout extends CardLayout{
    private JPanel controlPanel;

<<<<<<< HEAD
    public static class ControlGameLayout implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent){

=======
    public void setControlPanel(JPanel panel){ controlPanel = panel; }
    public GameLayoutAction getGameLayoutAction(){
        return new GameLayoutAction();
    }

    public class GameLayoutAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            next(controlPanel);
>>>>>>> c5ea83de31d73ffb6c741cdf12e79134b75f6ad0
        }
    }
}
