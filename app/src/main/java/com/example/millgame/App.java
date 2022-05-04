package com.example.millgame;

public class App implements Runnable {
    private static final long serialVersionUID = 1L;

    private Thread threadGui;

    public App() {
        GameGUI gameGUI = new GameGUI();
        gameGUI.setVisible(true);
    }

    public static void main(String[] args){
        System.out.println("HELLO WORLD!");
        // COMMAND LINE PARSER - TO ENABLE LOG TO CONSOLE
        new App().start();
    }

    @Override
    public void run() {
        stop();
    }

    private void start() {
        threadGui = new Thread(this);
        threadGui.start();
    }

    public void stop() {
        try {
            threadGui.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}