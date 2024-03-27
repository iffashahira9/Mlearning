package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectedTopicModule extends AppCompatActivity {

    CardView moduleBodyPart,moduleAnimal;
    Button btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_topic_module);

        moduleAnimal= findViewById(R.id.moduleAnimal);
        moduleBodyPart = findViewById(R.id.moduleBodyPart);
        btnHome = findViewById(R.id.btnHome);

        moduleAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(selectedTopicModule.this,videoViewAnimal5.class));
            }
        });

        moduleBodyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectedTopicModule.this,videoViewBody5.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectedTopicModule.this,Home.class));
            }
        });
    }

    @Override
    //when click back button, it will navigate to home screen
    public void onBackPressed(){
        startActivity(new Intent(selectedTopicModule.this,Home.class));
    }
}