package com.example.millgame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStageIteratorTest {
    private GameStageIterator itr;

    @BeforeEach
    public void createIterator(){
        itr = GameStageIterator.create();
    }

    @Test
    public void firstGameStageTest(){
        MillGame.GameStage stage = itr.next();

        assertEquals(MillGame.GameStage.UNINITIATED, stage);
    }

    @Test
    public void playingGameStageTest(){
        MillGame.GameStage stage;
        int STAGES = 2; // stages to stay in PLAYING stage
        int counter = 0;

        while(counter < STAGES){
            itr.next();
            counter += 1;
        }

        stage = itr.next(); // now iterator is at PLAYING stage
        assertEquals(MillGame.GameStage.PLAYING, stage);
    }


    @Test
    public void resetGameStageIteratorTest() {
        int STAGES = 2; // number of iteration in GameStageIterator
        int count = 0;
        while(count < STAGES){
            itr.next();
            count += 1;
        }

        itr.reset(); // reset iterator
        assertEquals(MillGame.GameStage.UNINITIATED, itr.next());
    }

}
