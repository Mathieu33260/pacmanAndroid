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
                        System.out.println("bas droite chemin bas");
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY + 1] = GHOST_2;
                            System.out.println("bas droite chemin droite bis");
                        } else {
                            System.out.println("AAA");
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY + 1] = GHOST_2;
                        System.out.println("bas droite chemin droite");
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX + 1][posY] = GHOST_2;
                            System.out.println("bas droite chemin bas bis");
                        } else {
                            System.out.println("BBB");
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
                        System.out.println("bas gauche chemin bas");
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY - 1] = GHOST_2;
                            System.out.println("bas gauche chemin gauche bis");
                        } else {
                            System.out.println("CCC");
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY - 1] = GHOST_2;
                        System.out.print("bas gauche chemin gauche");
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX + 1][posY] = GHOST_2;
                            System.out.println("bas gauche chemin bas bis");
                        } else {
                            System.out.println("DDD");
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
                        System.out.println("haut droite chemin haut");
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY + 1] = GHOST_2;
                            System.out.println("haut droite chemin droite bis");
                        } else {
                            System.out.println("EEE");
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY + 1] = GHOST_2;
                        System.out.println("haut droite chemin droite");
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX - 1][posY] = GHOST_2;
                            System.out.println("haut droite chemin bas bis");
                        } else {
                            System.out.println("FFF");
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
                        System.out.println("haut gauche chemin haut");
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX][posY - 1] = GHOST_2;
                            System.out.println("haut gauche chemin gauche bis");
                        } else {
                            System.out.println("GGG");
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        matrice[posX][posY] = MIAM_BLOCK;
                        matrice[posX][posY - 1] = GHOST_2;
                        System.out.println("haut gauche chemin gauche");
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            matrice[posX][posY] = MIAM_BLOCK;
                            matrice[posX - 1][posY] = GHOST_2;
                            System.out.println("haut gauche chemin haut bis");
                        } else {
                            System.out.println("HHH");
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
