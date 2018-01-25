package com.example.mathieu.pacman;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mathieu on 22/01/2018.
 */

abstract class Ghost {

    protected int posX;
    protected int posY;
    protected String previousDirection;
    protected String nextDirection;

    protected String tempBlock;

    protected static final String WHITE_BLOCK = "9";

    protected static final String MIAM_BLOCK = "1";

    protected static final String WALL_BLOCK = "0";

    protected static final String GHOST_1 = "6";

    protected static final String GHOST_2 = "7";

    protected static final String GHOST_3 = "8";

    protected static final String PACMAN_OPEN = "2";

    protected static final String PACMAN_OPEN_REVERSE = "3";

    protected static final String PACMAN_CLOSE = "4";

    protected static final String PACMAN_CLOSE_REVERSE = "5";

    Ghost(String previousDirection, String nextDirection)
    {
        this.previousDirection = previousDirection;
        this.nextDirection = nextDirection;
    }

    abstract String[][] nextDirection(String[][] matrice, int posXpacman, int posYpacman);

    abstract int getPosX();

    abstract void setPosX(int posX);

    abstract int getPosY();

    abstract void setPosY(int posY);

    abstract String getPreviousDirection();

    abstract void setTempBlock(String[][] matrice, String direction);

    abstract void setPreviousDirection(String previousDirection);

    abstract String getNextDirection();

    abstract void setNextDirection(String nextDirection);
}
