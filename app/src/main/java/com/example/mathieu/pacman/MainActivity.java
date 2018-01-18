package com.example.mathieu.pacman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private int timer;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageAdapter = new ImageAdapter(this);

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
                        if(!imageAdapter.getNextDirection().equals(""))
                        {
                            imageAdapter.updateView(imageAdapter.getNextDirection());
                            imageAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }, 100, 100);

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

    public void left_button_click(View view) {
        imageAdapter.setNextDirection("left");
    }

    public void top_button_click(View view) {
        imageAdapter.setNextDirection("top");
    }

    public void bottom_button_click(View view) {
        imageAdapter.setNextDirection("bottom");
    }

    public void right_button_click(View view) {
        imageAdapter.setNextDirection("right");
    }
}
