package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home2 extends AppCompatActivity {
 private CardView cardPlayQuiz6,cardModule6,cardAR_6;
 ImageView backBtnTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        cardPlayQuiz6=findViewById(R.id.cardPlayQuiz6);
        cardModule6=findViewById(R.id.cardModule6);
        cardAR_6=findViewById(R.id.cardAR_6);
        backBtnTopic=findViewById(R.id.backBtnTopic);

        cardPlayQuiz6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent home button quiz to selected topic age 6 years old
                startActivity(new Intent(Home2.this,selectedTopicAge6.class));
            }
        });
        cardAR_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent home button AR to Ar main menu
                startActivity(new Intent(Home2.this,ARmainMenu.class));
            }
        });
        cardModule6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent home button Module to selected topic module age 6
                startActivity(new Intent(Home2.this,selectedTopicModule6.class));
            }
        });
    }
    @Override
    //when click back button, it will navigate to home screen
    public void onBackPressed(){
        startActivity(new Intent(Home2.this,AgeCategory.class));
        finish();
    }
}