package com.example.mathieu.pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mathieu on 22/01/2018.
 */

public class GhostRandom extends Ghost{

    GhostRandom(String previousDirection, String nextDirection)
    {
        super(previousDirection,nextDirection);

        tempBlock = "";
    }

    public String[][] nextDirection(String[][] matrice, int posXpacman, int posYpacman)
    {
        ArrayList<String> dir = new ArrayList<>();

        if(!matrice[posX - 1][posY].equals(WALL_BLOCK) && !previousDirection.equals("bottom"))
        {
            dir.add("top");
        }
        if(!matrice[posX][posY + 1].equals(WALL_BLOCK) && !previousDirection.equals("left"))
        {
            dir.add("right");
        }
        if(!matrice[posX + 1][posY].equals(WALL_BLOCK) && !previousDirection.equals("top"))
        {
            dir.add("bottom");
        }
        if(!matrice[posX][posY - 1].equals(WALL_BLOCK) && !previousDirection.equals("right"))
        {
            dir.add("left");
        }

        Random random = new Random();

        int select = random.nextInt(dir.size());

        switch (dir.get(select))
        {
            case "top":
                setTempBlock(matrice, "top");
                matrice[posX - 1][posY] = GHOST_1;
                posX--;
                break;

            case "bottom":
                setTempBlock(matrice, "bottom");
                matrice[posX + 1][posY] = GHOST_1;
                posX++;
                break;

            case "left":
                setTempBlock(matrice, "left");
                matrice[posX][posY - 1] = GHOST_1;
                posY--;
                break;

            case "right":
                setTempBlock(matrice, "right");
                matrice[posX][posY + 1] = GHOST_1;
                posY++;
                break;
        }

        previousDirection = dir.get(select);

        return matrice;
    }

    @Override
    int getPosX() {
        return posX;
    }

    @Override
    void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    int getPosY() {
        return posY;
    }

    @Override
    void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    String getPreviousDirection() {
        return previousDirection;
    }

    @Override
    void setPreviousDirection(String previousDirection) {
        this.previousDirection = previousDirection;
    }

    @Override
    String getNextDirection() {
        return nextDirection;
    }

    @Override
    void setNextDirection(String nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    void setTempBlock(String[][] matrice, String direction) {
        if (tempBlock.equals("")) {
            matrice[posX][posY] = MIAM_BLOCK;
        } else {
            matrice[posX][posY] = tempBlock;
        }
        if (direction.equals("top")) {
            if (matrice[posX - 1][posY].equals(WHITE_BLOCK)) {
                tempBlock = WHITE_BLOCK;
            } else {
                tempBlock = MIAM_BLOCK;
            }
        } else if (direction.equals("bottom")) {
            if (matrice[posX + 1][posY].equals(WHITE_BLOCK)) {
                tempBlock = WHITE_BLOCK;
            } else {
                tempBlock = MIAM_BLOCK;
            }
        } else if (direction.equals("left")) {
            if (matrice[posX][posY - 1].equals(WHITE_BLOCK)) {
                tempBlock = WHITE_BLOCK;
            } else {
                tempBlock = MIAM_BLOCK;
            }
        } else if (direction.equals("right")) {
            if (matrice[posX][posY + 1].equals(WHITE_BLOCK)) {
                tempBlock = WHITE_BLOCK;
            } else {
                tempBlock = MIAM_BLOCK;
            }
        }

    }
}
