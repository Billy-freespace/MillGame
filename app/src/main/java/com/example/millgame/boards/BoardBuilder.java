package com.example.millgame.boards;

import com.example.millgame.Board;

public interface BoardBuilder {
    void reset();

    Board build();
}
