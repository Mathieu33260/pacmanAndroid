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

    private ArrayList<Integer> tabGrid = new ArrayList<>();

    public ImageAdapter(Context c) {
        mContext = c;
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

        System.out.println(matrice());
        imageView.setImageResource(mThumbIds[Integer.parseInt(str[position])]);
        return imageView;
    }

    public String[][] matrice()
    {
        String[][] tab1 = new String[5][11];
        String str[] = getStr();

        int cpt = 0;
        int cpt2 = 0;

        for(String[] t:tab1) {
            for(int i=cpt*11;i<(cpt+1)*11;i++) {
                t[cpt2] = str[i];
                cpt2++;
            }
            cpt++;
        }

        return tab1;
    }

  /*  public View updateView(int position, View convertView, ViewGroup parent, String direction) {
        ImageView imageView;
        String str[] = getStr();

        for (String s:str) {
            if (s.equals("2")) {
                String pacman = s;
            }
        }

        switch (direction) {
            case "top":
                break;
            case "right":
                break;
            case "bottom":
                break;
            case "left":
                break;
        }
        return imageView;
    } */

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.mur, R.drawable.miam,  R.drawable.pacman, R.drawable.ghost1, R.drawable.ghost2, R.drawable.ghost3,

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

}