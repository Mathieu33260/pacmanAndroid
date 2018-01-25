package com.example.mathieu.pacman;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mathieu on 22/01/2018.
 */

public class GhostEvil extends Ghost{

    GhostEvil(String previousDirection, String nextDirection)
    {
        super(previousDirection,nextDirection);

        tempBlock = "";
    }

    public String[][] nextDirection(String[][] matrice, int posXpacman, int posYpacman)
    {
        if(posXpacman - posX >= 0) //bas
        {
            if(posYpacman - posY >= 0) //droite
            {
                if(Math.abs(posXpacman - posX) > Math.abs(posYpacman - posY)) //plus de chemin vers le bas
                {
                    if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "bottom");
                        matrice[posX + 1][posY] = GHOST_2;
                        posX++;
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "right");
                            matrice[posX][posY + 1] = GHOST_2;
                            posY++;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "right");
                        matrice[posX][posY + 1] = GHOST_2;
                        posY++;
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "bottom");
                            matrice[posX + 1][posY] = GHOST_2;
                            posX++;
                        } else {
                        }
                    }
                }
            } else { //gauche
                if(Math.abs(posXpacman - posX) > Math.abs(posYpacman - posY)) //plus de chemin vers le bas
                {
                    if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "bottom");
                        matrice[posX + 1][posY] = GHOST_2;
                        posX++;
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "left");
                            matrice[posX][posY - 1] = GHOST_2;
                            posY--;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "left");
                        matrice[posX][posY - 1] = GHOST_2;
                        posY--;
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "bottom");
                            matrice[posX + 1][posY] = GHOST_2;
                            posX++;
                        } else {
                        }
                    }
                }
            }
        } else { //haut
            if(posYpacman - posY > 0) //droite
            {
                if(Math.abs(posXpacman - posX) > Math.abs(posYpacman - posY)) //plus de chemin vers le haut
                {
                    if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "top");
                        matrice[posX - 1][posY] = GHOST_2;
                        posX--;
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "right");
                            matrice[posX][posY + 1] = GHOST_2;
                            posY++;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "right");
                        matrice[posX][posY + 1] = GHOST_2;
                        posY++;
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "top");
                            matrice[posX - 1][posY] = GHOST_2;
                            posX--;
                        } else {
                        }
                    }
                }
            } else { //gauche
                if(Math.abs(posXpacman - posX) > Math.abs(posYpacman - posY)) //plus de chemin vers le haut
                {
                    if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "top");
                        matrice[posX - 1][posY] = GHOST_2;
                        posX--;
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "left");
                            matrice[posX][posY - 1] = GHOST_2;
                            posY--;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "left");
                        matrice[posX][posY - 1] = GHOST_2;
                        posY--;
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "top");
                            matrice[posX - 1][posY] = GHOST_2;
                            posX--;
                        } else {
                        }
                    }
                }
            }
        }

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
