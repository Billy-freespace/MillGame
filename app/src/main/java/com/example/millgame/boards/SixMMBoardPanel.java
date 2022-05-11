package com.example.millgame.boards;

import com.example.millgame.Position;
import com.example.millgame.graphicsAndSounds.Assets;

import java.awt.*;
import java.util.HashMap;

public class SixMMBoardPanel extends BoardPanel {
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'e';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 5;

    private GridBagLayout layoutManager;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(Assets.background, 0, 0, null);

        unmark();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.weighty = 1;

        for(Character xLabel : positions.keySet()){
            HashMap<Integer, Position> inner = positions.get(xLabel);
            for(Integer yLabel : inner.keySet()){
                Position position = inner.get(yLabel);
                if(position != null){
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
                }
            }
        }
    }

    SixMMBoardPanel(){
        super();
        layoutManager = new GridBagLayout();
        setLayout(layoutManager);
        System.out.println("SixMMBoardPanel creation");
        //board positions definition
        /*
         * square : a1-e1-e5-a5
         */

        Position a1 = new Position('a', 1);

        origin = a1;

        // a1 (origin) neighbours
        Position c1 = new Position('c', 1);
        Position a3 = new Position('a', 3);

        a1.addNeighbour(c1);
        a1.addNeighbour(a3);

        addPosition(a1);

        // c1 neighbours
        Position c2 = new Position('c', 2);
        Position e1 = new Position('e', 1);

        c1.addNeighbour(c2);
        c1.addNeighbour(e1);
        c1.addNeighbour(a1);

        addPosition(c1);

        // e1 neighbours
        Position e3 = new Position('e', 3);

        e1.addNeighbour(c1);
        e1.addNeighbour(e3);

        addPosition(e1);

        // e3 neighbours
        Position d3 = new Position('d', 3);
        Position e5 = new Position('e', 5);

        e3.addNeighbour(e1);
        e3.addNeighbour(d3);
        e3.addNeighbour(e5);

        addPosition(e3);

        // e5 neighbours
        Position c5 = new Position('c', 5);

        e5.addNeighbour(e3);
        e5.addNeighbour(c5);

        addPosition(e5);

        // c5 neighbours
        Position c4 = new Position('c', 4);
        Position a5 = new Position('a', 5);

        c5.addNeighbour(e5);
        c5.addNeighbour(c4);
        c5.addNeighbour(a5);

        addPosition(c5);

        // a5 neighbours
        a5.addNeighbour(c5);
        a5.addNeighbour(a3);

        addPosition(a5);

        // a3 neighbours
        Position b3 = new Position('b', 3);

        a3.addNeighbour(a5);
        a3.addNeighbour(b3);
        a3.addNeighbour(a1);

        addPosition(a3);


        /*
         * square : b2-d2-d4-b4
         */

        Position b2 = new Position('b', 2);

        // b2 neighbours
        b2.addNeighbour(b3);
        b2.addNeighbour(c2);

        addPosition(b2);

        // c2 neighbours
        Position d2 = new Position('d', 2);

        c2.addNeighbour(b2);
        c2.addNeighbour(c1);
        c2.addNeighbour(d2);

        addPosition(c2);

        // d2 neighbours
        d2.addNeighbour(c2);
        d2.addNeighbour(d3);

        addPosition(d2);

        // d3 neighbours
        Position d4 = new Position('d', 4);

        d3.addNeighbour(d2);
        d3.addNeighbour(e3);
        d3.addNeighbour(d4);

        addPosition(d3);

        // d4 neighbours
        d4.addNeighbour(d3);
        d4.addNeighbour(c4);

        addPosition(d4);

        // c4 neighbours
        Position b4 = new Position('b', 4);

        c4.addNeighbour(d4);
        c4.addNeighbour(c5);
        c4.addNeighbour(b4);

        addPosition(c4);

        // b4 neighbours
        b4.addNeighbour(c4);
        b4.addNeighbour(b3);

        addPosition(b4);

        // b3 neighbours
        b3.addNeighbour(b4);
        b3.addNeighbour(a3);
        b3.addNeighbour(b2);

        addPosition(b3);
    }
}
