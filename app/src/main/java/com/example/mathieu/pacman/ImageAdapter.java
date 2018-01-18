package com.example.mathieu.pacman;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private String currentDirection = "";

    private String nextDirection = "";

    private String[][] matrice = new String[5][11];

    public ImageAdapter(Context c) {
        mContext = c;
        matrice = matrice(getStr());
    }

    public int getCount() {return getStr().length;}

    public String[] getStr()
    {
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.grid);
        String stringFile = readTextFile(inputStream);
        String textStr[] = stringFile.split("\\r\\n|\\n|\\r");
        String ss = "";
        for (String t:textStr) {
            ss += t;
        }
        String str[] = ss.split(",");
        return str;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        String str[] = getStr();


        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[Integer.parseInt(matrice[position/11][position%11])]);
        return imageView;
    }

    public String[][] matrice(String str[])
    {
        String[][] tab1 = new String[5][11];

        int cpt = 0;
        int cpt2 = 0;

        for(String[] t:tab1) {
            for(int i=cpt*11;i<(cpt+1)*11;i++) {
                t[cpt2] = str[i];
                cpt2++;
            }
            cpt++;
            cpt2 = 0;
        }

        return tab1;
    }

    public void updateView(String nextDirection) {

        int[] pacman = new int[2];

        for(int i=0; i<matrice.length; i++)
        {
            for(int j=0; j<matrice[i].length; j++)
            {
                if (matrice[i][j].equals("2")) {
                    pacman[0] = i;
                    pacman[1] = j;
                }
            }
        }

        if (currentDirection.equals("")) {
            currentDirection = nextDirection;
        }

        switch (nextDirection) {

            case "top":
                if (!matrice[pacman[0] - 1][pacman[1]].equals("0")) {
                    currentDirection = nextDirection;
                }
                break;
            case "right":
                if (!matrice[pacman[0]][pacman[1] + 1].equals("0")) {
                    currentDirection = nextDirection;
                }
                break;
            case "bottom":
                if (!matrice[pacman[0] + 1][pacman[1]].equals("0")) {
                    currentDirection = nextDirection;
                }
                break;
            case "left":
                if (!matrice[pacman[0]][pacman[1] - 1].equals("0")) {
                    currentDirection = nextDirection;
                }
                break;
        }

        switch (currentDirection) {

            case "top":
                if (!matrice[pacman[0] - 1][pacman[1]].equals("0")) {
                    matrice[pacman[0]][pacman[1]] = "6";
                    matrice[pacman[0] - 1][pacman[1]] = "2";
                }
                break;
            case "right":
                if (!matrice[pacman[0]][pacman[1] + 1].equals("0")) {
                    matrice[pacman[0]][pacman[1]] = "6";
                    matrice[pacman[0]][pacman[1] + 1] = "2";
                }
                break;
            case "bottom":
                if (!matrice[pacman[0] + 1][pacman[1]].equals("0")) {
                    matrice[pacman[0]][pacman[1]] = "6";
                    matrice[pacman[0] + 1][pacman[1]] = "2";
                }
                break;
            case "left":
                if (!matrice[pacman[0]][pacman[1] - 1].equals("0")) {
                    matrice[pacman[0]][pacman[1]] = "6";
                    matrice[pacman[0]][pacman[1] - 1] = "2";
                }
                break;
        }
    }



    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.mur, R.drawable.miam,  R.drawable.pacman, R.drawable.ghost1, R.drawable.ghost2,
            R.drawable.ghost3, R.drawable.white
    };

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public String getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(String nextDirection) {
        this.nextDirection = nextDirection;
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }
}