package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.Position;
import com.example.millgame.exceptions.InvalidPositionCoordinate;
import com.example.millgame.graphicsAndSounds.Assets;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private GridBagLayout layoutManager;
    protected Board board;

    public BoardPanel(Board board){
        super();
        layoutManager = new GridBagLayout();
        setLayout(layoutManager);
        this.board = board;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(Assets.background, 0, 0, null);

        board.unmark();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.weighty = 1;

        char MIN_XLABEL = board.minXLabel();
        char MAX_XLABEL = board.maxXLabel();
        int MIN_YLABEL = board.minYLabel();
        int MAX_YLABEL = board.maxYLabel();

        for(char x=MIN_XLABEL; x<=MAX_XLABEL; x++){
            for(int y=MIN_YLABEL; y<=MAX_YLABEL; y++){
                try{
                    Position position = board.getPosition(x, y);
                    gbc.gridx = (int) position.getXLabel() - (int) MIN_XLABEL;
                    gbc.gridy = MAX_YLABEL - position.getYLabel();

                    add(position, gbc);

                    for(Position neighbour : position.getNeighbours()){
                        if(!neighbour.mark){
                            g.setColor(new Color(0, 0, 0));
                            g.drawLine(position.getX() + position.getHeight()/2, position.getY() + position.getWidth()/2,
                                    neighbour.getX() + neighbour.getHeight()/2, neighbour.getY() + neighbour.getWidth()/2);
                        }
                    }
                } catch (InvalidPositionCoordinate error){
                    continue;
                }
            }
        }
    }
}
