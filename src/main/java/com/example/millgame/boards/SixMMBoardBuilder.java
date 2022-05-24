package com.example.millgame.boards;

import com.example.millgame.Position;

public class SixMMBoardBuilder extends BoardBuilder {

    public void reset() {
        board = new SixMMBoard(playerColors);
    }

    public void createPositions() {
        // DEFINIR POSITIONS
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

        board.addPosition(a1);

        // c1 neighbours
        Position c2 = new Position('c', 2);
        Position e1 = new Position('e', 1);

        c1.addNeighbour(c2);
        c1.addNeighbour(e1);
        c1.addNeighbour(a1);

        board.addPosition(c1);

        // e1 neighbours
        Position e3 = new Position('e', 3);

        e1.addNeighbour(c1);
        e1.addNeighbour(e3);

        board.addPosition(e1);

        // e3 neighbours
        Position d3 = new Position('d', 3);
        Position e5 = new Position('e', 5);

        e3.addNeighbour(e1);
        e3.addNeighbour(d3);
        e3.addNeighbour(e5);

        board.addPosition(e3);

        // e5 neighbours
        Position c5 = new Position('c', 5);

        e5.addNeighbour(e3);
        e5.addNeighbour(c5);

        board.addPosition(e5);

        // c5 neighbours
        Position c4 = new Position('c', 4);
        Position a5 = new Position('a', 5);

        c5.addNeighbour(e5);
        c5.addNeighbour(c4);
        c5.addNeighbour(a5);

        board.addPosition(c5);

        // a5 neighbours
        a5.addNeighbour(c5);
        a5.addNeighbour(a3);

        board.addPosition(a5);

        // a3 neighbours
        Position b3 = new Position('b', 3);

        a3.addNeighbour(a5);
        a3.addNeighbour(b3);
        a3.addNeighbour(a1);

        board.addPosition(a3);


        /*
         * square : b2-d2-d4-b4
         */

        Position b2 = new Position('b', 2);

        // b2 neighbours
        b2.addNeighbour(b3);
        b2.addNeighbour(c2);

        board.addPosition(b2);

        // c2 neighbours
        Position d2 = new Position('d', 2);

        c2.addNeighbour(b2);
        c2.addNeighbour(c1);
        c2.addNeighbour(d2);

        board.addPosition(c2);

        // d2 neighbours
        d2.addNeighbour(c2);
        d2.addNeighbour(d3);

        board.addPosition(d2);

        // d3 neighbours
        Position d4 = new Position('d', 4);

        d3.addNeighbour(d2);
        d3.addNeighbour(e3);
        d3.addNeighbour(d4);

        board.addPosition(d3);

        // d4 neighbours
        d4.addNeighbour(d3);
        d4.addNeighbour(c4);

        board.addPosition(d4);

        // c4 neighbours
        Position b4 = new Position('b', 4);

        c4.addNeighbour(d4);
        c4.addNeighbour(c5);
        c4.addNeighbour(b4);

        board.addPosition(c4);

        // b4 neighbours
        b4.addNeighbour(c4);
        b4.addNeighbour(b3);

        board.addPosition(b4);

        // b3 neighbours
        b3.addNeighbour(b4);
        b3.addNeighbour(a3);
        b3.addNeighbour(b2);

        board.addPosition(b3);
    }
}