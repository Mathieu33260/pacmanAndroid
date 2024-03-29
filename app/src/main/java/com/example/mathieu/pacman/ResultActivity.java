package com.example.mathieu.pacman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView scoreLabel;
    TextView highScoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(Integer.toString(score) + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
             highScoreLabel.setText(Integer.toString(score));

             //save
             SharedPreferences.Editor editor = settings.edit();
             editor.putInt("HIGH_SCORE", score);
             editor.commit();
        } else {
            highScoreLabel.setText(Integer.toString(highScore));
        }

    }

    public void tryAgain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
