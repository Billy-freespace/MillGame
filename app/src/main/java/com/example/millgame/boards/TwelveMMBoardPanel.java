package com.example.millgame.boards;

import com.example.millgame.Position;

import java.awt.*;
import java.util.HashMap;

public class TwelveMMBoardPanel extends BoardPanel{
    static final char MIN_XLABEL = 'a';
    static final char MAX_XLABEL = 'g';
    static final int MIN_YLABEL = 1;
    static final int MAX_YLABEL = 7;

    private GridBagLayout layoutManager;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

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
                            g.drawLine(position.getX(), position.getY(),
                                    neighbour.getX(), neighbour.getY());
                        }
                    }
                }
            }
        }
    }
    TwelveMMBoardPanel(){
        super();

        layoutManager = new GridBagLayout();
        setLayout(layoutManager);
        System.out.println("TwelveMMBoardPanel creation");

        // board positions definition

        /*
         * square : a1-g1-g7-a7
         */

        Position a1 = new Position('a', 1);

        origin = a1;

        // a1 (origin) neighbours
        Position d1 = new Position('d', 1);
        Position a4 = new Position('a', 4);
        Position b2 = new Position('b', 2);

        a1.addNeighbour(d1);
        a1.addNeighbour(a4);
        a1.addNeighbour(b2);

        addPosition(a1);

        // d1 neighbours
        Position d2 = new Position('d', 2);
        Position g1 = new Position('g', 1);

        d1.addNeighbour(d2);
        d1.addNeighbour(g1);
        d1.addNeighbour(a1);

        addPosition(d1);

        // g1 neighbours
        Position g4 = new Position('g', 4);
        Position f2 = new Position('f', 2);

        g1.addNeighbour(g4);
        g1.addNeighbour(d1);
        g1.addNeighbour(f2);

        addPosition(g1);

        // g4 neighbours
        Position f4 = new Position('f', 4);
        Position g7 = new Position('g', 7);

        g4.addNeighbour(f4);
        g4.addNeighbour(g7);
        g4.addNeighbour(g1);

        addPosition(g4);


        // g7 neighbours
        Position d7 = new Position('d', 7);
        Position f6 = new Position('f', 6);

        g7.addNeighbour(d7);
        g7.addNeighbour(g4);
        g7.addNeighbour(f6);

        addPosition(g7);

        // d7 neighbours
        Position a7 = new Position('a', 7);
        Position d6 = new Position('d', 6);

        d7.addNeighbour(a7);
        d7.addNeighbour(d6);
        d7.addNeighbour(g7);

        addPosition(d7);

        // a7 neighbours
        Position b6 = new Position('b', 6);

        a7.addNeighbour(a4);
        a7.addNeighbour(d7);
        a7.addNeighbour(b6);

        addPosition(a7);


        // a4 neighbours
        Position b4 = new Position('b', 4);

        a4.addNeighbour(b4);
        a4.addNeighbour(a7);
        a4.addNeighbour(a1);

        addPosition(a4);

        /*
         * square : b2-f2-f6-b6
         */

        // b2 neighbours
        Position c3 = new Position('c', 3);

        b2.addNeighbour(a1);
        b2.addNeighbour(b4);
        b2.addNeighbour(d2);
        b2.addNeighbour(c3);

        addPosition(b2);

        // d2 neighbours
        Position d3 = new Position('d', 3);

        d2.addNeighbour(f2);
        d2.addNeighbour(d3);
        d2.addNeighbour(b2);
        d2.addNeighbour(d1);

        addPosition(d2);


        // f2 neighbours
        Position e3 = new Position('e', 3);

        f2.addNeighbour(g1);
        f2.addNeighbour(f4);
        f2.addNeighbour(d2);
        f2.addNeighbour(e3);

        addPosition(f2);

        // f4 neighbours
        Position e4 = new Position('e', 4);

        f4.addNeighbour(e4);
        f4.addNeighbour(f6);
        f4.addNeighbour(g4);
        f4.addNeighbour(f2);

        addPosition(f4);

        // f6 neighbours
        Position e5 = new Position('e', 5);

        f6.addNeighbour(g7);
        f6.addNeighbour(d6);
        f6.addNeighbour(f4);
        f6.addNeighbour(e5);

        addPosition(f6);

        // d6 neighbours
        Position d5 = new Position('d', 5);

        d6.addNeighbour(b6);
        d6.addNeighbour(d5);
        d6.addNeighbour(f6);
        d6.addNeighbour(d7);

        addPosition(d6);

        // b6 neighbours
        Position c5 = new Position('c', 5);

        b6.addNeighbour(a7);
        b6.addNeighbour(b4);
        b6.addNeighbour(d6);
        b6.addNeighbour(c5);

        addPosition(b6);

        // b4 neighbours
        Position c4 = new Position('c', 4);

        b4.addNeighbour(c4);
        b4.addNeighbour(a4);
        b4.addNeighbour(b2);
        b4.addNeighbour(b6);

        addPosition(b4);

        /*
         * square : c3-e3-e5-c5
         */

        // c3 neighbours
        c3.addNeighbour(b2);
        c3.addNeighbour(d3);
        c3.addNeighbour(c4);

        addPosition(c3);

        // d3 neighbours
        d3.addNeighbour(e3);
        d3.addNeighbour(c3);

        addPosition(d3);

        // e3 neighbours
        e3.addNeighbour(f2);
        e3.addNeighbour(e4);
        e3.addNeighbour(e4);

        addPosition(e3);

        // e4 neighbours
        e4.addNeighbour(e5);
        e4.addNeighbour(e3);
        e4.addNeighbour(f4);

        addPosition(e4);

        // e5 neighbours
        e5.addNeighbour(f6);
        e5.addNeighbour(d5);
        e5.addNeighbour(e4);

        addPosition(e5);

        // d5 neighbours
        d5.addNeighbour(c5);
        d5.addNeighbour(e5);
        d5.addNeighbour(d6);

        addPosition(d5);

        // c5 neighbours
        c5.addNeighbour(b6);
        c5.addNeighbour(d5);
        c5.addNeighbour(c4);

        addPosition(c5);

        // c4 neighbours
        c4.addNeighbour(c5);
        c4.addNeighbour(c3);

        addPosition(c4);
    }
}
