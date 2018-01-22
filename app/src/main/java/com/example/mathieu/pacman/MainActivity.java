package com.example.mathieu.pacman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private int timer;
    private int count;

    private static final int NUMBER_COLUMNS = 19;

    private static final int NUMBER_LINES = 6;

    private static final String WHITE_BLOCK = "7";

    private static final String WALL_BLOCK = "0";

    private String pacmanRotation;

    private String[][] matrice = new String[NUMBER_LINES][NUMBER_COLUMNS];

    private String currentDirection = "";

    private String nextDirection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pacmanRotation = "2";
        matrice = matrice(getStr());

        imageAdapter = new ImageAdapter(this,getStr().length,matrice,NUMBER_COLUMNS);

        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        count++;
                        if(!nextDirection.equals(""))
                        {
                            updateView(nextDirection);
                            imageAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }, 300, 300);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateView(String nextDirection) {

        int[] pacman = new int[2];

        for(int i=0; i<matrice.length; i++)
        {
            for(int j=0; j<matrice[i].length; j++)
            {
                if (matrice[i][j].equals(pacmanRotation)) {
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
                if (!matrice[pacman[0] - 1][pacman[1]].equals(WALL_BLOCK)) {
                    currentDirection = nextDirection;
                }
                break;
            case "right":
                if (!matrice[pacman[0]][pacman[1] + 1].equals(WALL_BLOCK)) {
                    pacmanRotation = "2";
                    currentDirection = nextDirection;
                }
                break;
            case "bottom":
                if (!matrice[pacman[0] + 1][pacman[1]].equals(WALL_BLOCK)) {
                    currentDirection = nextDirection;
                }
                break;
            case "left":
                if (!matrice[pacman[0]][pacman[1] - 1].equals(WALL_BLOCK)) {
                    pacmanRotation = "3";
                    currentDirection = nextDirection;
                }
                break;
        }

        switch (currentDirection) {

            case "top":
                if (!matrice[pacman[0] - 1][pacman[1]].equals(WALL_BLOCK)) {
                    matrice[pacman[0]][pacman[1]] = WHITE_BLOCK;
                    matrice[pacman[0] - 1][pacman[1]] = pacmanRotation;
                }
                break;
            case "right":
                if (!matrice[pacman[0]][pacman[1] + 1].equals(WALL_BLOCK)) {
                    matrice[pacman[0]][pacman[1]] = WHITE_BLOCK;
                    matrice[pacman[0]][pacman[1] + 1] = pacmanRotation;
                }
                break;
            case "bottom":
                if (!matrice[pacman[0] + 1][pacman[1]].equals(WALL_BLOCK)) {
                    matrice[pacman[0]][pacman[1]] = WHITE_BLOCK;
                    matrice[pacman[0] + 1][pacman[1]] = pacmanRotation;
                }
                break;
            case "left":
                if (!matrice[pacman[0]][pacman[1] - 1].equals(WALL_BLOCK)) {
                    matrice[pacman[0]][pacman[1]] = WHITE_BLOCK;
                    matrice[pacman[0]][pacman[1] - 1] = pacmanRotation;
                }
                break;
        }
        imageAdapter.setMatrice(matrice);
    }

    public String[] getStr()
    {
        InputStream inputStream = getResources().openRawResource(R.raw.grid);
        String stringFile = readTextFile(inputStream);
        String textStr[] = stringFile.split("\\r\\n|\\n|\\r");
        String ss = "";
        for (String t:textStr) {
            ss += t;
        }
        String str[] = ss.split(",");
        return str;
    }

    public String[][] matrice(String str[])
    {
        String[][] tab1 = new String[NUMBER_LINES][NUMBER_COLUMNS];

        int cpt = 0;
        int cpt2 = 0;

        for(String[] t:tab1) {
            for(int i=cpt*NUMBER_COLUMNS;i<(cpt+1)*NUMBER_COLUMNS;i++) {
                System.out.println(str[i]);
                t[cpt2] = str[i];
                cpt2++;
            }
            cpt++;
            cpt2 = 0;
        }

        return tab1;
    }

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

    public void left_button_click(View view) {
        nextDirection = "left";
    }

    public void top_button_click(View view) {
        nextDirection = "top";
    }

    public void bottom_button_click(View view) {
        nextDirection = "bottom";
    }

    public void right_button_click(View view) {
        nextDirection = "right";
    }
}
