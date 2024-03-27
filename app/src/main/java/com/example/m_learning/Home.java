package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

  private CardView cardPlayQuiz,cardAR,cardModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardPlayQuiz=findViewById(R.id.cardPlayQuiz);
        cardAR = findViewById(R.id.cardAR);
        cardModule = findViewById(R.id.cardModule);

        cardPlayQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent home button quiz to selected topic 5 years old
                startActivity(new Intent(Home.this,selectedTopicAge5.class));
            }
        });

        cardAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent home button AR to Ar main menu
                startActivity(new Intent(Home.this,ARmainMenu.class));
            }
        });
        cardModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent home button module to selected topic module
                startActivity(new Intent(Home.this,selectedTopicModule.class));
            }
        });

    }
    @Override
    //when click back button, it will navigate to home screen
    public void onBackPressed(){
        startActivity(new Intent(Home.this,AgeCategory.class));
        finish();
    }
}