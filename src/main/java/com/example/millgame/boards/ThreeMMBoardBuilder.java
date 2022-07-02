package com.example.millgame.boards;

import com.example.millgame.Position;

public class ThreeMMBoardBuilder extends BoardBuilder {
    // COPIED FROM SixMMBoardBuilder (UPDATE, REMOVE EXTRA POSITIONS)

    public void reset() {
        board = new SixMMBoard(playerColors);
    }

    public void createPositions() {
        // DEFINIR POSITIONS
        /*
         * square : a1-c1-c3-a3
         */

        Position a1 = new Position('a', 1);

        origin = a1;

        // a1 (origin) neighbours
        Position a2 = new Position('a', 2);
        Position b1 = new Position('b', 1);
        Position b2 = new Position('b', 2);

        a1.addNeighbour(a2);
        a1.addNeighbour(b1);
        a1.addNeighbour(b2);

        board.addPosition(a1);

        // b1 neighbours
        Position c1 = new Position('c', 1);

        b1.addNeighbour(a1);
        b1.addNeighbour(b2);
        b1.addNeighbour(c1);

        board.addPosition(b1);

        // c1 neighbours
        Position c2 = new Position('c', 2);

        c1.addNeighbour(b1);
        c1.addNeighbour(b2);
        c1.addNeighbour(c2);

        board.addPosition(c1);

        // c2 neighbours
        Position c3 = new Position('c', 3);

        c2.addNeighbour(c1);
        c2.addNeighbour(b2);
        c2.addNeighbour(c3);

        board.addPosition(c2);

        // c3 neighbours
        Position b3 = new Position('b', 3);

        c3.addNeighbour(c2);
        c3.addNeighbour(b2);
        c3.addNeighbour(b3);

        board.addPosition(c3);

        // b3 neighbours
        Position a3 = new Position('a', 3);

        b3.addNeighbour(c3);
        b3.addNeighbour(b2);
        b3.addNeighbour(a3);

        board.addPosition(b3);

        // a3 neighbours
        a3.addNeighbour(b3);
        a3.addNeighbour(b2);
        a3.addNeighbour(a2);

        board.addPosition(a3);

        // a2 neighbours
        a2.addNeighbour(a3);
        a2.addNeighbour(b2);
        a2.addNeighbour(a1);

        board.addPosition(a2);

        // b2 neighbours
        b2.addNeighbour(a2);
        b2.addNeighbour(b1);
        b2.addNeighbour(c2);
        b2.addNeighbour(b3);

        board.addPosition(b2);
    }
}