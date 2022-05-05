package com.example.millgame.graphicsAndSounds;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background, board, whitePiece, blackPiece, pointDisabled;
    public static Clip buttonPressed, pieceMoved, pieceRemoved, pieceSelected, gameOver, millFormed, pointPressed;

    public static void init() {
//        Images
        background = Loader.ImageLoader("/textures/nmm_background.png");
        board = Loader.ImageLoader("/textures/nmm_board.png");
        whitePiece = Loader.ImageLoader("/textures/nmm_coin-white-normal.png");
        blackPiece = Loader.ImageLoader("/textures/nmm_coin-black-normal.png");
        pointDisabled = Loader.ImageLoader("/textures/nmm_point-disabled.png");

//        Sounds
        buttonPressed = Loader.loadSound("/audio/sfx/button-pressed.wav");
        pieceMoved = Loader.loadSound("/audio/sfx/coin-moved.wav");
        pieceRemoved = Loader.loadSound("/audio/sfx/coin-removed.wav");
        pieceSelected = Loader.loadSound("/audio/sfx/coin-selected.wav");
        gameOver = Loader.loadSound("/audio/sfx/game-over.wav");
        millFormed = Loader.loadSound("/audio/sfx/mill-formed.wav");
        pointPressed = Loader.loadSound("/audio/sfx/point-pressed.wav");
    }

}
