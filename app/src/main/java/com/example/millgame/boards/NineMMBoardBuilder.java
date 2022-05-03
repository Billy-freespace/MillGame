package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.BoardBuilder;
import com.example.millgame.Position;

public class NineMMBoardBuilder implements BoardBuilder {
    private NineMMBoard board;
    private int diagonals;
    private int squares;
    private Position origin;

    public void reset() {
        board = new NineMMBoard();
    }

    public void setDiagonals(int n) {
        diagonals = n;
    }

    public void setSquares(int n) {
        squares = n;
    }

    public NineMMBoard build(){
        reset();
        setSquares(3);
        setDiagonals(0);


        // board positions definition

        Position origin = new Position('a', 1);
        Position d1 = new Position('d', 1);
        Position a4 = new Position('a', 4);

        origin.addNeighbour(d1);
        origin.addNeighbour(a4);

        Position d2 = new Position('d', 2);
        Position g1 = new Position('g', 1);

        d1.addNeighbour(d2);
        d1.addNeighbour(g1);

        Position a7 = new Position('a', 7);
        Position b4 = new Position('b', 4);

        a4.addNeighbour(a7);
        a4.addNeighbour(b4);




        board.setOrigin(origin);

        return board;
    }

}