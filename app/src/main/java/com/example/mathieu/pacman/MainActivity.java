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

    private static final String PACMAN = "2";

    private static final String PACMAN_REVERSE = "3";

    private static final String MIAM_BLOCK = "1";

    private String[][] matrice = new String[NUMBER_LINES][NUMBER_COLUMNS];

    private int cptMiam;

    Pacman pacman;

    Ghost ghostRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrice = matrice(getStr());

        imageAdapter = new ImageAdapter(this,getStr().length,matrice,NUMBER_COLUMNS);

        pacman = new Pacman("","","2");
        ghostRandom = new Ghost("","RANDOM");

        cptMiam = 0;

        for(int i=0; i<matrice.length; i++)
        {
            for(int j=0; j<matrice[i].length; j++) {
                if (matrice[i][j].equals(pacman.getPacmanRotation())) {
                    pacman.setPosX(i);
                    pacman.setPosY(j);
                }

                if (matrice[i][j].equals("5")) {
                    ghostRandom.setPosX(i);
                    ghostRandom.setPosY(j);

                }

                if (matrice[i][j].equals(MIAM_BLOCK)) {
                    cptMiam++;
                }
            }
        }

        final Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (cptMiam == 0) {
                            T.cancel();
                            System.out.println("J'ai gagné");
                        }
                        count++;
                        updateView(pacman.getNextDirection());
                    }
                });
            }
        }, 260, 260);

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

        for(int i=0; i<matrice.length; i++)
        {
            for(int j=0; j<matrice[i].length; j++)
            {
                if (matrice[i][j].equals(pacman.getPacmanRotation())) {
                    pacman.setPosX(i);
                    pacman.setPosY(j);
                }
                if (matrice[i][j].equals("5")) {
                    ghostRandom.setPosX(i);
                    ghostRandom.setPosY(j);
                }
            }
        }

        matrice = ghostRandom.nextDirection(matrice);
        imageAdapter.setMatrice(matrice);

        if (pacman.getCurrentDirection().equals("")) {
            pacman.setCurrentDirection(nextDirection);
        }

        switch (nextDirection) {

            case "top":
                if (!matrice[pacman.getPosX() - 1][pacman.getPosY()].equals(WALL_BLOCK)) {
                    pacman.setCurrentDirection(nextDirection);
                }
                break;
            case "right":
                if (!matrice[pacman.getPosX()][pacman.getPosY() + 1].equals(WALL_BLOCK)) {
                    pacman.setPacmanRotation(PACMAN);
                    pacman.setCurrentDirection(nextDirection);
                }
                break;
            case "bottom":
                if (!matrice[pacman.getPosX() + 1][pacman.getPosY()].equals(WALL_BLOCK)) {
                    pacman.setCurrentDirection(nextDirection);
                }
                break;
            case "left":
                if (!matrice[pacman.getPosX()][pacman.getPosY() - 1].equals(WALL_BLOCK)) {
                    pacman.setPacmanRotation(PACMAN_REVERSE);
                    pacman.setCurrentDirection(nextDirection);
                }
                break;
        }

        switch (pacman.getCurrentDirection()) {

            case "top":
                if (!matrice[pacman.getPosX() - 1][pacman.getPosY()].equals(WALL_BLOCK)) {
                    if (matrice[pacman.getPosX() - 1][pacman.getPosY()].equals(MIAM_BLOCK)) {
                        cptMiam--;
                    }
                    matrice[pacman.getPosX()][pacman.getPosY()] = WHITE_BLOCK;
                    matrice[pacman.getPosX() - 1][pacman.getPosY()] = pacman.getPacmanRotation();
                }
                break;
            case "right":
                if (!matrice[pacman.getPosX()][pacman.getPosY() + 1].equals(WALL_BLOCK)) {
                    if (matrice[pacman.getPosX()][pacman.getPosY() + 1].equals(MIAM_BLOCK)) {
                        cptMiam--;
                    }
                    matrice[pacman.getPosX()][pacman.getPosY()] = WHITE_BLOCK;
                    matrice[pacman.getPosX()][pacman.getPosY() + 1] = pacman.getPacmanRotation();
                }
                break;
            case "bottom":
                if (!matrice[pacman.getPosX() + 1][pacman.getPosY()].equals(WALL_BLOCK)) {
                    if (matrice[pacman.getPosX() + 1][pacman.getPosY()].equals(MIAM_BLOCK)) {
                        cptMiam--;
                    }
                    matrice[pacman.getPosX()][pacman.getPosY()] = WHITE_BLOCK;
                    matrice[pacman.getPosX() + 1][pacman.getPosY()] = pacman.getPacmanRotation();
                }
                break;
            case "left":
                if (!matrice[pacman.getPosX()][pacman.getPosY() - 1].equals(WALL_BLOCK)) {
                    if (matrice[pacman.getPosX()][pacman.getPosY() - 1].equals(MIAM_BLOCK)) {
                        cptMiam--;
                    }
                    matrice[pacman.getPosX()][pacman.getPosY()] = WHITE_BLOCK;
                    matrice[pacman.getPosX()][pacman.getPosY() - 1] = pacman.getPacmanRotation();
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
        pacman.setNextDirection("left");
    }

    public void top_button_click(View view) {
        pacman.setNextDirection("top");
    }

    public void bottom_button_click(View view) {
        pacman.setNextDirection("bottom");
    }

    public void right_button_click(View view) {
        pacman.setNextDirection("right");
    }
}
