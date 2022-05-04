package com.example.millgame;

import com.example.millgame.gameObjects.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class MillGameGUI extends JFrame implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    //    ups = updates per second
    private static int ups = 0;
    //    fps = frame per second
    private static int fps = 0;

    private Thread threadGui;
    private volatile boolean running;

    public MillGameGUI() {
        setTitle(Constants.title);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLayout(new GameLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel jPanelMain = new JPanel();

        jPanelMain.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        jPanelMain.setFocusable(true);

        add(jPanelMain);
        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        new MillGameGUI().start();
    }

    private void update() {
        ups++;
    }

    private void draw() {
        fps++;
    }

    @Override
    public void run() {
        long updateReference = System.nanoTime();
        long counterReference = System.nanoTime();
        double elapsedTime;
        double delta = 0;

        while (running) {
            Thread.onSpinWait();
            long loopStart = System.nanoTime();

            elapsedTime = loopStart - updateReference;
            updateReference = loopStart;

            delta += elapsedTime / Constants.NS_PER_UPDATE;

            while (delta >= 1) {
                update();
                delta--;
            }

            draw();

            if (System.nanoTime() - counterReference >= Constants.NS_PER_SECOND) {
                setTitle(Constants.title + " || UPS: " + ups + " || FPS: " + fps);
                ups = 0;
                fps = 0;
                counterReference = System.nanoTime();
            }

        }

        stop();

    }

    private void start() {
        threadGui = new Thread(this);
        running = true;
        threadGui.start();
    }

    private void stop() {
        try {
            running = false;
            threadGui.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
