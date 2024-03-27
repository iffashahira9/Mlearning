package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectedTopicModule6 extends AppCompatActivity {
    CardView moduleBodyPart6,moduleAnimal6;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_topic_module6);

        btnHome = findViewById(R.id.btnHome);
        moduleBodyPart6 =findViewById(R.id.moduleBodyPart6);
        moduleAnimal6 = findViewById(R.id.moduleAnimal6);

        moduleBodyPart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectedTopicModule6.this,videoViewBody6.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // enable button to change page to home class
                Intent intent = new Intent(selectedTopicModule6.this, Home2.class);
                startActivity(intent);
            }
        });

    }
    @Override
    //when click back button, it will navigate to home screen
    public void onBackPressed(){
        startActivity(new Intent(selectedTopicModule6.this,Home2.class));
    }
}