package com.example.mathieu.pacman;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private int size;

    private String[][] matrice;

    private int nb_column;

    public ImageAdapter(Context c, int size, String[][] matrice, int nb_column) {
        mContext = c;
        this.size = size;
        this.matrice = matrice;
        this.nb_column = nb_column;
    }

    public int getCount() {return size;}

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(56, 56));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[Integer.parseInt(matrice[position / nb_column][position % nb_column])]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.mur, R.drawable.miam,  R.drawable.pacman, R.drawable.pacman_reverse, R.drawable.ghost1, R.drawable.ghost2,
            R.drawable.ghost3, R.drawable.white
    };

    public void setMatrice(String[][] matrice) {
        this.matrice = matrice;
    }
}