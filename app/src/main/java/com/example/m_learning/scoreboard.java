package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class scoreboard extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView scoreText,test;
    Button btnFinish;
    //int correct,wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        scoreText = findViewById(R.id.scoreText);
        btnFinish = findViewById(R.id.btnFinish);


       final int getCorrectAns = getIntent().getIntExtra("correct",0);
       final int  getWrongAns  = getIntent().getIntExtra("incorrect",0);

        circularProgressBar.setProgress(getCorrectAns);
        scoreText.setText(getCorrectAns+"/"+(getCorrectAns+getWrongAns));

       // test.setText(getCorrectAns);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scoreboard.this, selectedTopicAge5.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void onBackPressed(){
        finish();
    }
}