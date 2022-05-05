package com.example.millgame.gameObjects;

public class Constants {
    //     JFrame game title
    public static final String title = "Mill Game";

    //     JFrame Dimensions
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    //     Timing
//     NS = nanoseconds
    public static final long NS_PER_SECOND = 1000000000;
    //     UPS = updates per second
    public static final byte UPS_TARGET = 60;
    public static final double NS_PER_UPDATE = NS_PER_SECOND / UPS_TARGET;
}
