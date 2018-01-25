package com.example.mathieu.pacman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by Mathieu on 22/01/2018.
 */

public class GhostSmart extends Ghost{

    private List<int[]> intersections;

    GhostSmart(String previousDirection, String nextDirection, String[][] matrice)
    {
        super(previousDirection,nextDirection);
        intersections = intersections(matrice);
        tempBlock = "";
    }

    @Override
    String[][] nextDirection(String[][] matrice, int posXpacman, int posYpacman)
    {
        List<int[]> list = intersectionsOrdo(posXpacman,posYpacman);

        if(posXpacman - list.get(0)[0] >= 0) //bas
        {
            if(posYpacman - list.get(0)[1] >= 0) //droite
            {
                if(Math.abs(posXpacman - list.get(0)[0]) > Math.abs(posYpacman - list.get(0)[1])) //plus de chemin vers le bas
                {
                    if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "bottom");
                        matrice[posX + 1][posY] = GHOST_3;
                        posX++;
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "right");
                            matrice[posX][posY + 1] = GHOST_3;
                            posY++;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "right");
                        matrice[posX][posY + 1] = GHOST_3;
                        posY++;
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "bottom");
                            matrice[posX + 1][posY] = GHOST_3;
                            posX++;
                        } else {
                        }
                    }
                }
            } else { //gauche
                if(Math.abs(posXpacman - list.get(0)[0]) > Math.abs(posYpacman - list.get(0)[1])) //plus de chemin vers le bas
                {
                    if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "bottom");
                        matrice[posX + 1][posY] = GHOST_3;
                        posX++;
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "left");
                            matrice[posX][posY - 1] = GHOST_3;
                            posY--;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "left");
                        matrice[posX][posY - 1] = GHOST_3;
                        posY--;
                    } else {
                        if(!matrice[posX + 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "bottom");
                            matrice[posX + 1][posY] = GHOST_3;
                            posX++;
                        } else {
                        }
                    }
                }
            }
        } else { //haut
            if(list.get(0)[1] - list.get(0)[1] > 0) //droite
            {
                if(Math.abs(posXpacman - list.get(0)[0]) > Math.abs(posYpacman - list.get(0)[1])) //plus de chemin vers le haut
                {
                    if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "top");
                        matrice[posX - 1][posY] = GHOST_3;
                        posX--;
                    } else {
                        if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "right");
                            matrice[posX][posY + 1] = GHOST_3;
                            posY++;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la droite
                    if(!matrice[posX][posY + 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "right");
                        matrice[posX][posY + 1] = GHOST_3;
                        posY++;
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "top");
                            matrice[posX - 1][posY] = GHOST_3;
                            posX--;
                        } else {
                        }
                    }
                }
            } else { //gauche
                if(Math.abs(posXpacman - list.get(0)[0]) > Math.abs(posYpacman - list.get(0)[1])) //plus de chemin vers le haut
                {
                    if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "top");
                        matrice[posX - 1][posY] = GHOST_3;
                        posX--;
                    } else {
                        if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "left");
                            matrice[posX][posY - 1] = GHOST_3;
                            posY--;
                        } else {
                        }
                    }
                } else { //plus de chemin vers la gauche
                    if(!matrice[posX][posY - 1].equals(WALL_BLOCK))
                    {
                        setTempBlock(matrice, "left");
                        matrice[posX][posY - 1] = GHOST_3;
                        posY--;
                    } else {
                        if(!matrice[posX - 1][posY].equals(WALL_BLOCK))
                        {
                            setTempBlock(matrice, "top");
                            matrice[posX - 1][posY] = GHOST_3;
                            posX--;
                        } else {
                        }
                    }
                }
            }
        }

        return matrice;
    }

    public List<int[]> intersections(String[][] matrice)
    {
        List<int[]> list = new ArrayList<>();

        int cpt;
        for(int i=1; i<matrice.length-1; i++)
        {
            for(int j=1; j<matrice[i].length-1; j++)
            {
                cpt = 0;
                if(!Objects.equals(matrice[i][j], WALL_BLOCK))
                {
                    if(!Objects.equals(matrice[i - 1][j], WALL_BLOCK))
                    {
                        cpt++;
                    }
                    if(!Objects.equals(matrice[i + 1][j], WALL_BLOCK))
                    {
                        cpt++;
                    }
                    if(!Objects.equals(matrice[i][j - 1], WALL_BLOCK))
                    {
                        cpt++;
                    }
                    if(!Objects.equals(matrice[i][j + 1], WALL_BLOCK))
                    {
                        cpt++;
                    }
                }

                if(cpt >= 3)
                {
                    int tab[] = {i,j};
                    list.add(tab);
                }
            }
        }
        return list;
    }

    public List<int[]> intersectionsOrdo(int posXpacman, int posYpacman)
    {
        List<int[]> list = new ArrayList<>();

        Map<int[],Double> map = new HashMap<>();

        for(int[] coord:intersections)
        {
            double dist = Math.sqrt(((coord[1] - coord[0]) * (coord[1] - coord[0])) +
                    ((posYpacman - posXpacman) * (posYpacman - posXpacman)));
            map.put(coord,dist);
        }

        Map<int[],Double> map2 = sortMapByValues(map);

        for(Map.Entry<int[],Double> entry: map2.entrySet()) {
            System.out.println(entry.getKey()[0] + " " + entry.getKey()[1] + " " + entry.getValue());
            list.add(entry.getKey());
        }

        return list;
    }

    private Map<int[], Double> sortMapByValues(Map<int[],Double> aMap) {

        Set<Entry<int[],Double>> mapEntries = aMap.entrySet();

        // used linked list to sort, because insertion of elements in linked list is faster than an array list.
        List<Entry<int[],Double>> aList = new LinkedList<Entry<int[],Double>>(mapEntries);

        // sorting the List
        Collections.sort(aList, new Comparator<Entry<int[],Double>>() {

            @Override
            public int compare(Entry<int[],Double> ele1,
                               Entry<int[],Double> ele2) {

                return ele1.getValue().compareTo(ele2.getValue());
            }
        });

        // Storing the list into Linked HashMap to preserve the order of insertion.
        Map<int[],Double> aMap2 = new LinkedHashMap<int[],Double>();
        for(Entry<int[],Double> entry: aList) {
            aMap2.put(entry.getKey(), entry.getValue());
        }

        return aMap2;
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

    public List<int[]> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<int[]> intersections) {
        this.intersections = intersections;
    }
}
