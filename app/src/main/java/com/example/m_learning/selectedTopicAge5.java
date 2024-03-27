package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class selectedTopicAge5 extends AppCompatActivity {

    //topic name to read table name from db
    private String TopicName="";
    CardView cardPlayQuizAnimal,cardPlayQuizBodyPart;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_topic_age5);

        cardPlayQuizBodyPart =findViewById(R.id.cardPlayQuizBodyPart);
        cardPlayQuizAnimal = findViewById(R.id.cardPlayQuizAnimal);
        btnHome =findViewById(R.id.btnHome);

        cardPlayQuizAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define "" by table name in db
                TopicName="Animals";
                //cardPlayQuizAnimal.setBackgroundResource(R.drawable.round_back_white_stroke10);
                //cardPlayQuizBodyPart.setBackgroundResource(R.drawable.round_back_white10);
                quizStart();

            }
        });

        cardPlayQuizBodyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define "" by table in db
                TopicName="BodyParts";
                //cardPlayQuizBodyPart.setBackgroundResource(R.drawable.round_back_white_stroke10);
                //cardPlayQuizAnimal.setBackgroundResource(R.drawable.round_back_white10);
                quizStart();

            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // enable button to change page to home class
                Intent intent = new Intent(selectedTopicAge5.this, Home.class);
                startActivity(intent);
            }
        });

    }
    public void quizStart(){
        if(TopicName.isEmpty()){
            Toast.makeText(selectedTopicAge5.this, "Please select the topic", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(selectedTopicAge5.this, quiz_age_5.class);
            intent.putExtra("Topic",TopicName);
            startActivity(intent);
        }
    }
    @Override
    //when click back button, it will navigate to home screen
    public void onBackPressed(){
        startActivity(new Intent(selectedTopicAge5.this,Home.class));
        finish();
    }

}