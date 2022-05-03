package com.example.millgame.boards;

import com.example.millgame.Position;

public class NineMMBoardBuilder implements BoardBuilder {
    private NineMMBoard board;
    private Position origin;

    public void reset() {
        board = new NineMMBoard();
    }

    public NineMMBoard build(){
        reset();

        // board positions definition

        /*
         * square : a1-g1-g7-a7
         */

        Position origin = new Position('a', 1);
        board.setPosition('a', 1, origin);

        // origin (a1) neighbours
        Position d1 = new Position('d', 1);
        Position a4 = new Position('a', 4);

        origin.addNeighbour(d1);
        origin.addNeighbour(a4);

        board.setPosition('d', 1, d1);
        board.setPosition('a', 4, a4);

        // d1 neighbours
        Position d2 = new Position('d', 2);
        Position g1 = new Position('g', 1);

        d1.addNeighbour(d2);
        d1.addNeighbour(g1);

        board.setPosition('d', 2, d2);
        board.setPosition('g', 1, g1);

        // g1 neighbours
        Position g4 = new Position('g', 4);

        g1.addNeighbour(g4);

        board.setPosition('g', 4, g4);

        // g4 neighbours
        Position f4 = new Position('f', 4);
        Position g7 = new Position('g', 7);

        g4.addNeighbour(f4);
        g4.addNeighbour(g7);

        board.setPosition('f', 4, f4);
        board.setPosition('g', 7, g7);

        // g7 neighbours
        Position d7 = new Position('d', 7);

        g7.addNeighbour(d7);

        board.setPosition('d', 7, d7);

        // d7 neighbours
        Position a7 = new Position('a', 7);
        Position d6 = new Position('d', 6);

        d7.addNeighbour(a7);
        d7.addNeighbour(d6);

        board.setPosition('a', 7, a7);
        board.setPosition('d', 6, d6);

        // a7 neighbours
        a7.addNeighbour(a4);

        // a4 neighbours
        Position b4 = new Position('b', 4);
        a4.addNeighbour(b4);

        board.setPosition('b', 4, b4);


        /*
         * square : b2-f2-f6-b6
         */

        Position b2 = new Position('b', 2);

        // b2 neighbours
        b2.addNeighbour(b4);
        b2.addNeighbour(d2);

        board.setPosition('b', 2, b2);

        // d2 neighbours
        Position f2 = new Position('f', 2);
        Position d3 = new Position('d', 3);

        d2.addNeighbour(f2);
        d2.addNeighbour(d3);

        board.setPosition('f', 2, f2);
        board.setPosition('d', 3, d3);

        // f2 neighbours
        f2.addNeighbour(f4);

        // f4 neighbours
        Position e4 = new Position('e', 4);
        Position f6 = new Position('e', 6);

        f4.addNeighbour(e4);
        f4.addNeighbour(f6);

        board.setPosition('e', 4, e4);
        board.setPosition('f', 6, f6);

        // f6 neighbours
        f6.addNeighbour(d6);

        // d6 neighbours
        Position b6 = new Position('b', 6);
        Position d5 = new Position('d', 5);

        d6.addNeighbour(b6);
        d6.addNeighbour(d5);

        board.setPosition('b', 6, b6);
        board.setPosition('d', 5, d5);

        // b6 neighbours
        b6.addNeighbour(b4);

        // b4 neighbours
        Position c4 = new Position('c', 4);

        b4.addNeighbour(c4);

        board.setPosition('c', 4, c4);

        /*
         * square : c3-e3-e5-c5
         */

        Position c3 = new Position('c', 3);

        // c3 neighbours
        c3.addNeighbour(d3);
        c3.addNeighbour(c4);

        board.setPosition('c', 3, c3);

        // d3 neighbours
        Position e3 = new Position('e', 3);

        d3.addNeighbour(e3);

        board.setPosition('e', 3, e3);

        // e3 neighbours
        e3.addNeighbour(e4);

        // e4 neighbours
        Position e5 = new Position('e', 5);
        e4.addNeighbour(e5);

        board.setPosition('e', 5, e5);

        // e5 neighbours
        e5.addNeighbour(d5);

        // d5 neighbours
        Position c5 = new Position('c', 5);

        d5.addNeighbour(c5);
        c5.addNeighbour(c4);

        board.setPosition('c', 5, c5);

        board.setOrigin(origin);

        return board;
    }
}