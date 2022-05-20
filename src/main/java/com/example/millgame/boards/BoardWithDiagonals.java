package com.example.millgame.boards;

import com.example.millgame.Board;
import com.example.millgame.Piece;
import com.example.millgame.PieceRadar;
import com.example.millgame.exceptions.InvalidMill;
import com.example.millgame.exceptions.InvalidMillColor;
import com.example.millgame.exceptions.InvalidMillSize;
import com.example.millgame.logging.TraceLogger;
import com.example.millgame.misc.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardWithDiagonals extends Board {


    public BoardWithDiagonals(BoardVariant variant){
        super(variant);
        radar = new PieceRadar(List.of(Direction.values()), this);
    }
    @Override
    public List<Board.Mill> getMills(Piece piece) {
        List<Board.Mill> mills = new ArrayList<Board.Mill>();

        radar.map(piece);

        // COMPLETE THIS STUFF

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
