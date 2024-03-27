package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class selectedTopicAge6 extends AppCompatActivity {
    private String TopicName="";
    CardView cardPlayQuizAnimal6,cardPlayQuizBodyPart6;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_topic_age6);

        cardPlayQuizBodyPart6 = findViewById(R.id.cardPlayQuizBodyPart6);
        cardPlayQuizAnimal6 = findViewById(R.id.cardPlayQuizAnimal6);
        btnHome = findViewById(R.id.btnHome);

        cardPlayQuizAnimal6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define "" by table name in db
                TopicName="AnimalsAge6";
                //cardPlayQuizAnimal6.setBackgroundResource(R.drawable.round_back_white_stroke10);
                //cardPlayQuizBodyPart6.setBackgroundResource(R.drawable.round_back_white10);
                quizStart();
            }
        });

        cardPlayQuizBodyPart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define "" by table name in db
                TopicName="BodyPartsAge6";
               // cardPlayQuizBodyPart6.setBackgroundResource(R.drawable.round_back_white_stroke10);
                //cardPlayQuizAnimal6.setBackgroundResource(R.drawable.round_back_white10);
                quizStart();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // enable button to change page to home class
                Intent intent = new Intent(selectedTopicAge6.this, Home2.class);
                startActivity(intent);
            }
        });
    }
    public void quizStart(){
        if(TopicName.isEmpty()){
            Toast.makeText(selectedTopicAge6.this, "Please select the topic", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(selectedTopicAge6.this, quiz_age_6.class);
            intent.putExtra("Topic",TopicName);
            startActivity(intent);
        }
    }
    @Override
    //when click back button, it will navigate to home screen
    public void onBackPressed(){
        startActivity(new Intent(selectedTopicAge6.this,Home2.class));
        finish();
    }
}