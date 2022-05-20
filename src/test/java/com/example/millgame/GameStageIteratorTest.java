package com.example.millgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class GameStageIteratorTest {
    private GameStageIterator itr;

    @BeforeEach
    public void createIterator(){
        itr = GameStageIterator.init();
    }

    @Test
    public void firstGameStageTest(){
        MillGame.GameStage stage = itr.next();

        assertEquals(MillGame.GameStage.POSITIONING, stage);
        assertEquals(MillGame.GameStage.POSITIONING, itr.getIterationState());
    }

    /*
    @Test
    public void playingGameStageTest(){
        MillGame.GameStage stage;
        int STAGES = 2; // stages to stay in GAMEOVER stage -1
        int counter = 0;

        while(counter < STAGES){
            itr.next();
            counter += 1;
        }

        stage = itr.next(); // now iterator is at GAMEOVER stage
        assertEquals(MillGame.GameStage.GAMEOVER, stage);
    }
     */


    // PROPERTY TEST (IMPLEMENT)
    @Test
    public void resetGameStageIteratorTest() {
        int STAGES = new Random().nextInt(10) + 1; // number of stages to iterate
        int count = 0;

        while(count < STAGES){
            itr.next();
            count += 1;
        }


        itr.reset(); // reset iterator
        assertEquals(MillGame.GameStage.POSITIONING, itr.next());
    }

}
