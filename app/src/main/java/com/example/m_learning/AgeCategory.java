package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgeCategory extends AppCompatActivity {

    Button btnAge5,btnAge6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_category);

        btnAge5 = findViewById(R.id.btnAge5);
        btnAge6 = findViewById(R.id.btnAge6);

        btnAge5.setOnClickListener(new View.OnClickListener() {
            @Override
            //intent button age 5 to home age 5
            public void onClick(View v) {
                startActivity(new Intent(AgeCategory.this,Home.class));
            }
        });

        btnAge6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent button age 6 to home age 6
                startActivity(new Intent(AgeCategory.this,Home2.class));
            }
        });
    }

    public void onBackPressed(){
        startActivity(new Intent(AgeCategory.this,MainActivity.class));
        finish();
    }
}