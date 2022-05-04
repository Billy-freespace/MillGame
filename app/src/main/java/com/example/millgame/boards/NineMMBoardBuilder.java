package com.example.millgame.boards;

import com.example.millgame.Position;

public class NineMMBoardBuilder implements BoardBuilder {
    private NineMMBoard board;
    private Position origin;

    public void reset() {
        board = new NineMMBoard();
    }

    public NineMMBoard build() {
        reset();

        // board positions definition

        BoardPositions boardPositions = new BoardPositions();

        /*
         * square : a1-g1-g7-a7
         */

        Position a1 = new Position('a', 1);

        board.setOrigin(a1);

        // a1 (origin) neighbours
        Position d1 = new Position('d', 1);
        Position a4 = new Position('a', 4);

        a1.addNeighbour(d1);
        a1.addNeighbour(a4);

        boardPositions.setPosition('a', 1, a1);

        // d1 neighbours
        Position d2 = new Position('d', 2);
        Position g1 = new Position('g', 1);

        d1.addNeighbour(d2);
        d1.addNeighbour(g1);
        d1.addNeighbour(a1);

        boardPositions.setPosition('d', 1, d1);

        // g1 neighbours
        Position g4 = new Position('g', 4);

        g1.addNeighbour(g4);
        g1.addNeighbour(d1);

        boardPositions.setPosition('g', 1, g1);

        // g4 neighbours
        Position f4 = new Position('f', 4);
        Position g7 = new Position('g', 7);

        g4.addNeighbour(f4);
        g4.addNeighbour(g7);
        g4.addNeighbour(g1);

        boardPositions.setPosition('g', 4, g4);


        // g7 neighbours
        Position d7 = new Position('d', 7);

        g7.addNeighbour(d7);
        g7.addNeighbour(g4);

        boardPositions.setPosition('g', 7, g7);

        // d7 neighbours
        Position a7 = new Position('a', 7);
        Position d6 = new Position('d', 6);

        d7.addNeighbour(a7);
        d7.addNeighbour(d6);
        d7.addNeighbour(g7);

        boardPositions.setPosition('d', 7, d7);

        // a7 neighbours
        a7.addNeighbour(a4);
        a7.addNeighbour(d7);

        boardPositions.setPosition('a', 7, a7);


        // a4 neighbours
        Position b4 = new Position('b', 4);

        a4.addNeighbour(b4);
        a4.addNeighbour(a7);
        a4.addNeighbour(a1);

        boardPositions.setPosition('a', 4, a4);


        /*
         * square : b2-f2-f6-b6
         */

        Position b2 = new Position('b', 2);

        // b2 neighbours
        b2.addNeighbour(b4);
        b2.addNeighbour(d2);

        boardPositions.setPosition('b', 2, b2);

        // d2 neighbours
        Position f2 = new Position('f', 2);
        Position d3 = new Position('d', 3);

        d2.addNeighbour(f2);
        d2.addNeighbour(d3);
        d2.addNeighbour(b2);
        d2.addNeighbour(d1);

        boardPositions.setPosition('d', 2, d2);


        // f2 neighbours
        f2.addNeighbour(f4);
        f2.addNeighbour(d2);

        boardPositions.setPosition('f', 2, f2);

        // f4 neighbours
        Position e4 = new Position('e', 4);
        Position f6 = new Position('e', 6);

        f4.addNeighbour(e4);
        f4.addNeighbour(f6);
        f4.addNeighbour(g4);
        f4.addNeighbour(f2);

        boardPositions.setPosition('f', 4, f4);

        // f6 neighbours
        f6.addNeighbour(d6);
        f6.addNeighbour(f4);

        boardPositions.setPosition('f', 6, f6);

        // d6 neighbours
        Position b6 = new Position('b', 6);
        Position d5 = new Position('d', 5);

        d6.addNeighbour(b6);
        d6.addNeighbour(d5);
        d6.addNeighbour(f6);
        d6.addNeighbour(d7);

        boardPositions.setPosition('d', 6, d6);

        // b6 neighbours
        b6.addNeighbour(b4);
        b6.addNeighbour(d6);

        boardPositions.setPosition('b', 6, b6);

        // b4 neighbours
        Position c4 = new Position('c', 4);

        b4.addNeighbour(c4);
        b4.addNeighbour(a4);
        b4.addNeighbour(b2);
        b4.addNeighbour(b6);

        boardPositions.setPosition('b', 4, b4);

        /*
         * square : c3-e3-e5-c5
         */

        Position c3 = new Position('c', 3);

        // c3 neighbours
        c3.addNeighbour(d3);
        c3.addNeighbour(c4);

        boardPositions.setPosition('c', 3, c3);

        // d3 neighbours
        Position e3 = new Position('e', 3);

        d3.addNeighbour(e3);
        d3.addNeighbour(c3);

        boardPositions.setPosition('d', 3, d3);

        // e3 neighbours
        e3.addNeighbour(e4);
        e3.addNeighbour(e4);

        boardPositions.setPosition('e', 3, e3);

        // e4 neighbours
        Position e5 = new Position('e', 5);

        e4.addNeighbour(e5);
        e4.addNeighbour(e3);
        e4.addNeighbour(f4);

        boardPositions.setPosition('e', 4, e4);

        // e5 neighbours
        e5.addNeighbour(d5);
        e5.addNeighbour(e4);

        boardPositions.setPosition('e', 5, e5);

        // d5 neighbours
        Position c5 = new Position('c', 5);

        d5.addNeighbour(c5);
        d5.addNeighbour(e5);
        d5.addNeighbour(d6);

        boardPositions.setPosition('d', 5, d5);

        // c5 neighbours
        c5.addNeighbour(d5);
        c5.addNeighbour(c4);

        boardPositions.setPosition('c', 5, c5);

        // c4 neighbours
        c4.addNeighbour(c5);
        c4.addNeighbour(c3);

        boardPositions.setPosition('c', 4, c4);

        board.setPositions(boardPositions.getPositions());

        return board;
    }
}