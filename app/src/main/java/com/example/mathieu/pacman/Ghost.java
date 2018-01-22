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

    Ghost(String previousDirection, String type)
    {
        this.previousDirection = previousDirection;
        this.type = type;
    }

    public String[][] nextDirection(String[][] matrice)
    {
        ArrayList<String> dir = new ArrayList<>();

        if(!matrice[posX - 1][posY].equals("0") && !previousDirection.equals("top"))
        {
            dir.add("top");
        }
        if(!matrice[posX][posY + 1].equals("0") && !previousDirection.equals("right"))
        {
            dir.add("right");
        }
        if(!matrice[posX + 1][posY].equals("0") && !previousDirection.equals("bottom"))
        {
            dir.add("bottom");
        }
        if(!matrice[posX][posY - 1].equals("0") && !previousDirection.equals("left"))
        {
            dir.add("left");
        }

        Random random = new Random();

        int select = random.nextInt(dir.size());

        switch (dir.get(select))
        {
            case "top":
                matrice[posX][posY] = "7";
                matrice[posX - 1][posY] = "5";
                break;

            case "bottom":
                matrice[posX][posY] = "7";
                matrice[posX + 1][posY] = "5";
                break;

            case "left":
                matrice[posX][posY] = "7";
                matrice[posX][posY - 1] = "5";
                break;

            case "right":
                matrice[posX][posY] = "7";
                matrice[posX][posY + 1] = "5";
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
