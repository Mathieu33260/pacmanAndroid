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
    private int count2;
    private int count3;
    private int count4;

    private static final int NUMBER_COLUMNS = 19;

    private static final int NUMBER_LINES = 10;

    public static final String WALL_BLOCK = "0";

    public static final String MIAM_BLOCK = "1";

    public static final String PACMAN_OPEN = "2";

    public static final String PACMAN_OPEN_REVERSE = "3";

    public static final String PACMAN_CLOSE = "4";

    public static final String PACMAN_CLOSE_REVERSE = "5";

    public static final String GHOST_1 = "6";

    public static final String GHOST_2 = "7";

    public static final String GHOST_3 = "8";

    public static final String WHITE_BLOCK = "9";

    private String[][] matrice = new String[NUMBER_LINES][NUMBER_COLUMNS];

    private int cptMiam;

    private boolean pacmanOpenMouth;

    Pacman pacman;

    Ghost ghostRandom;
    Ghost ghostEvil;
    Ghost ghostSmart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrice = matrice(getStr());

        imageAdapter = new ImageAdapter(this,getStr().length,matrice,NUMBER_COLUMNS);

        pacman = new Pacman("","", PACMAN_OPEN);
        ghostRandom = new Ghost("","RANDOM");
        ghostEvil = new Ghost("","EVIL");
        ghostSmart = new Ghost("","SMART");

        cptMiam = 0;

        pacmanOpenMouth = true;

        for(int i=0; i<matrice.length; i++)
        {
            for(int j=0; j<matrice[i].length; j++) {
                if (matrice[i][j].equals(pacman.getPacmanRotation())) {
                    pacman.setPosX(i);
                    pacman.setPosY(j);
                }
                if (matrice[i][j].equals(GHOST_1)) {
                    ghostRandom.setPosX(i);
                    ghostRandom.setPosY(j);
                }

                if (matrice[i][j].equals(GHOST_2)) {
                    ghostEvil.setPosX(i);
                    ghostEvil.setPosY(j);
                }

                if (matrice[i][j].equals(GHOST_3)) {
                    ghostSmart.setPosX(i);
                    ghostSmart.setPosY(j);
                }

                if (matrice[i][j].equals(MIAM_BLOCK)) {
                    cptMiam++;
                }
            }
        }

        final Timer T_pacman=new Timer();
        T_pacman.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (cptMiam == 0) {
                            T_pacman.cancel();
                            System.out.println("J'ai gagné");
                        }
                        count++;
                        updateView(pacman.getNextDirection());
                        imageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, 260, 260);

        final Timer T_ghost1=new Timer();
        T_ghost1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        count2++;
                        updateGhost(ghostRandom);
                        imageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, 260, 260);

        final Timer T_ghost2=new Timer();
        T_ghost2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (cptMiam == 0) {
                            T_ghost2.cancel();
                            System.out.println("J'ai gagné");
                        }
                        count3++;
                        updateGhost(ghostEvil);
                        imageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, 260, 260);

        final Timer T_ghost3=new Timer();
        T_ghost3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (cptMiam == 0) {
                            T_ghost3.cancel();
                            System.out.println("J'ai gagné");
                        }
                        count4++;
                        updateGhost(ghostSmart);
                        imageAdapter.notifyDataSetChanged();
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

    public void updateGhost(Ghost ghost)
    {
        for(int i=0; i<matrice.length; i++)
        {
            for(int j=0; j<matrice[i].length; j++)
            {
                if (matrice[i][j].equals(GHOST_1) && ghost.getType().equals("RANDOM")) {
                    ghostRandom.setPosX(i);
                    ghostRandom.setPosY(j);
                }

                if (matrice[i][j].equals(GHOST_2) && ghost.getType().equals("EVIL")) {
                    ghostEvil.setPosX(i);
                    ghostEvil.setPosY(j);
                }

                if (matrice[i][j].equals(GHOST_3) && ghost.getType().equals("SMART")) {
                    ghostSmart.setPosX(i);
                    ghostSmart.setPosY(j);
                }
            }
        }

        matrice = ghost.nextDirection(matrice);
        imageAdapter.setMatrice(matrice);
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
            }
        }

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
                    if (pacmanOpenMouth) {
                        pacman.setPacmanRotation(PACMAN_CLOSE);
                        pacmanOpenMouth = false;
                    } else {
                        pacman.setPacmanRotation(PACMAN_OPEN);
                        pacmanOpenMouth = true;
                    }
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
                    if (pacmanOpenMouth) {
                        pacman.setPacmanRotation(PACMAN_CLOSE_REVERSE);
                        pacmanOpenMouth = false;
                    } else {
                        pacman.setPacmanRotation(PACMAN_OPEN_REVERSE);
                        pacmanOpenMouth = true;
                    }
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
