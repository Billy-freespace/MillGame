package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.MillGame;
import com.example.millgame.Piece;
import com.example.millgame.PieceRadar;
import com.example.millgame.exceptions.InvalidMill;
import com.example.millgame.exceptions.InvalidMillColor;
import com.example.millgame.exceptions.InvalidMillSize;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.CardinalDirection;
import com.example.millgame.misc.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class BoardWithoutDiagonals extends Board {

    public BoardWithoutDiagonals(MillGame.GameVariant variant){
        super(variant);
        radar = new PieceRadar(List.of(Direction.values()), this);
    }
    @Override
    public List<Board.Mill> getMills(Piece piece) {
        List<Board.Mill> mills = new ArrayList<Board.Mill>();
        List<List<Piece>> pieces = new ArrayList<List<Piece>>();

        radar.map(piece);

        //TraceLogger.log(Level.INFO, radar.toString());

        int verticalCount = radar.getCount(Direction.VERTICAL);
        int horizontalCount = radar.getCount(Direction.HORIZONTAL);

        if(verticalCount == 2){
            List<Piece> millPieces = radar.getPieces(Direction.VERTICAL);
            millPieces.add(piece);
            pieces.add(millPieces);
        }

        if(horizontalCount == 2){
            List<Piece> millPieces = radar.getPieces(Direction.HORIZONTAL);
            millPieces.add(piece);
            pieces.add(millPieces);
        }

        for(List<Piece> millPieces : pieces){
            Mill mill = createMill(millPieces);
            if(mill != null){
                mills.add(mill);
            }
        }

        return mills;
    }

    @Override
    public Mill createMill(List<Piece> pieces) {
        Mill mill = null;
        try{
            mill = new Mill(pieces);
        } catch (InvalidMill | InvalidMillSize | InvalidMillColor error){
            TraceLogger.log(error);
        }

        return mill;
    }

    public class Mill extends Board.Mill {

        public Mill(List<Piece> pieces) throws InvalidMill, InvalidMillSize, InvalidMillColor {
            super(pieces);
        }

        @Override
        protected boolean isValid(List<Piece> pieces){
            return true;
        }
    }
}

