package com.example.millgame;

import java.awt.*;

public class App {
    public static void main(String[] args){
        Runnable runner = new Runnable(){
            public void run(){
                System.out.println("HELLO WORLD!");
                // COMMAND LINE PARSER - TO ENABLE LOG TO CONSOLE

                GameGUI gameGUI = new GameGUI();
                gameGUI.setVisible(true);
            }
        };

        EventQueue.invokeLater(runner);
    }
}