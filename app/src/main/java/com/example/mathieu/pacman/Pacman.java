package com.example.mathieu.pacman;

/**
 * Created by Mathieu on 18/01/2018.
 */

public class Pacman {

    private static final int NUMBER_COLUMNS = 19;

    private static final int NUMBER_LINES = 10;

    public static final String WALL_BLOCK = "0";

    public static final String MIAM_BLOCK = "1";

    public static final String PACMAN_OPEN = "2";

    public static final String PACMAN_OPEN_REVERSE = "3";

    public static final String PACMAN_CLOSE = "4";

    public static final String PACMAN_CLOSE_REVERSE = "5";

    public static final String GHOST_1 = "6";

    public static final String GHOST_2 = "7";

    public static final String GHOST_3 = "8";

    public static final String WHITE_BLOCK = "9";

    private int posX;
    private int posY;
    private String pacmanRotation;
    private String currentDirection;
    private String nextDirection;

    Pacman(String currentDirection, String nextDirection, String pacmanRotation)
    {
        this.currentDirection = currentDirection;
        this.nextDirection = nextDirection;
        this.pacmanRotation = pacmanRotation;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(String nextDirection) {
        this.nextDirection = nextDirection;
    }

    public String getPacmanRotation() {
        return pacmanRotation;
    }

    public void setPacmanRotation(String pacmanRotation) {
        this.pacmanRotation = pacmanRotation;
    }
}
