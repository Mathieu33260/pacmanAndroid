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
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX + 1][posY] = GHOST_2;
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY + 1] = GHOST_2;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY + 1] = GHOST_2;
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX + 1][posY] = GHOST_2;
                        } else {
                        }
                    }
                }
            } else { //gauche
                if(Math.abs(posXpacman - posX) > Math.abs(posYpacman - posY)) //plus de chemin vers le bas
                {
                    if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX + 1][posY] = GHOST_2;
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY - 1] = GHOST_2;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY - 1] = GHOST_2;
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX + 1][posY] = GHOST_2;
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
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX - 1][posY] = GHOST_2;
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY + 1] = GHOST_2;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY + 1] = GHOST_2;
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX - 1][posY] = GHOST_2;
                        } else {
                        }
                    }
                }
            } else { //gauche
                if(Math.abs(posXpacman - posX) > Math.abs(posYpacman - posY)) //plus de chemin vers le haut
                {
                    if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX - 1][posY] = GHOST_2;
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY - 1] = GHOST_2;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY - 1] = GHOST_2;
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX - 1][posY] = GHOST_2;
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
}
