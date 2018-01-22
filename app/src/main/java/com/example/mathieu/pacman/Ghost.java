package com.example.mathieu.pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mathieu on 22/01/2018.
 */

public class Ghost {

    private String type;
    private int posX;
    private int posY;
    private String previousDirection;

    public static final String WHITE_BLOCK = "9";

    public static final String WALL_BLOCK = "0";

    public static final String GHOST_1 = "6";

    public static final String GHOST_2 = "7";

    public static final String GHOST_3 = "8";

    Ghost(String previousDirection, String type)
    {
        this.previousDirection = previousDirection;
        this.type = type;
    }

    public String[][] nextDirection(String[][] matrice)
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
                matrice[posX][posY] = WHITE_BLOCK;
                matrice[posX - 1][posY] = GHOST_1;
                break;

            case "bottom":
                matrice[posX][posY] = WHITE_BLOCK;
                matrice[posX + 1][posY] = GHOST_1;
                break;

            case "left":
                matrice[posX][posY] = WHITE_BLOCK;
                matrice[posX][posY - 1] = GHOST_1;
                break;

            case "right":
                matrice[posX][posY] = WHITE_BLOCK;
                matrice[posX][posY + 1] = GHOST_1;
                break;
        }

        previousDirection = dir.get(select);

        System.out.println(previousDirection);

        return matrice;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPreviousDirection() {
        return previousDirection;
    }

    public void setPreviousDirection(String previousDirection) {
        this.previousDirection = previousDirection;
    }
}
