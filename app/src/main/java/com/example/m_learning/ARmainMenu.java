package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ARmainMenu extends AppCompatActivity {
    Button btnStart,btnHowToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armain_menu);

        btnHowToPlay=findViewById(R.id.btnHowToPlay);
        btnStart=findViewById(R.id.btnStart);

        btnHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent how to play button module to tutorial
                startActivity(new Intent(ARmainMenu.this,ARhowToPlay.class));
            }
        });


    }
}