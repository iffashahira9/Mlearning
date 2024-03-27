package com.example.m_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class quiz_age_5 extends AppCompatActivity {

    private TextView questions,questionAge5;
    private AppCompatButton option1,option2,option3;
    private AppCompatButton btnNext;
    private int currentQuestionPosition = 0;

    //select answer option
    private String selectedOptionByUser = "";


    //list array for question
    private List <QuesList> quesLists = new ArrayList<>();

    //timer
    private  Timer timer;
    private int timeinMinute =0;
    private int second =0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_age5);

        final TextView topicName = findViewById(R.id.topicName);
        final  TextView timer = findViewById(R.id.timer);
        questions = findViewById(R.id.questions);
        option1 = findViewById(R.id.option1);
        option2 =findViewById(R.id.option2);
        option3 =findViewById(R.id.option3);
        questionAge5 = findViewById(R.id.questionAge5);
        btnNext = findViewById(R.id.btnNext);



        //TopicName is name value table in db "animals"
        //get topic name from db
    final String getTopicName = getIntent().getStringExtra("Topic");
    topicName.setText(getTopicName);


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mlearning-a7bd1-default-rtdb.firebaseio.com/");

    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            timeinMinute= Integer.parseInt(snapshot.child("time").getValue(String.class));
            for (DataSnapshot dataSnapshot: snapshot.child(getTopicName).getChildren()){
                //read and get value from table db
                final String getQuestion = dataSnapshot.child("question").getValue(String.class);
                final String getOption1 = dataSnapshot.child("option1").getValue(String.class);
                final String getOption2 = dataSnapshot.child("option2").getValue(String.class);
                final String getOption3 = dataSnapshot.child("option3").getValue(String.class);
                final String getAnswer = dataSnapshot.child("answer").getValue(String.class);

                QuesList quesList = new QuesList(getQuestion,getOption1,getOption2,getOption3,getAnswer);
                quesLists.add(quesList);
            }
            //set data
            questions.setText((currentQuestionPosition+1)+"/"+quesLists.size());
            questionAge5.setText(quesLists.get(currentQuestionPosition).getQuestion());
            option1.setText(quesLists.get(currentQuestionPosition).getOption1());
            option2.setText(quesLists.get(currentQuestionPosition).getOption2());
            option3.setText(quesLists.get(currentQuestionPosition).getOption3());

            startTimer(timer);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    });
    option1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser=option1.getText().toString();

                option1.setBackgroundResource(R.drawable.round_back_red);
                option1.setTextColor(Color.WHITE);

                showAns();
                quesLists.get(currentQuestionPosition).setUserSelectAns(selectedOptionByUser);
            }
        }
    });
    option2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser=option2.getText().toString();

                option2.setBackgroundResource(R.drawable.round_back_red);
                option2.setTextColor(Color.WHITE);

                showAns();
                quesLists.get(currentQuestionPosition).setUserSelectAns(selectedOptionByUser);
            }
        }
    });
    option3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser=option3.getText().toString();

                option3.setBackgroundResource(R.drawable.round_back_red);
                option3.setTextColor(Color.WHITE);

                showAns();
                quesLists.get(currentQuestionPosition).setUserSelectAns(selectedOptionByUser);
            }
        }
    });
    btnNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedOptionByUser.isEmpty()){
                Toast.makeText(quiz_age_5.this, "please choose answer", Toast.LENGTH_SHORT).show();
            }
            else{
                nextQuestion();
            }
        }
    });

    }
    private void nextQuestion (){
        currentQuestionPosition++;

        if((currentQuestionPosition+1) >= quesLists.size()){
            btnNext.setText("Submit Quiz");

            if((currentQuestionPosition+1) > quesLists.size()) {
               timer.purge();
               timer.cancel();
                //finish();

            }
        }

        if(currentQuestionPosition < quesLists.size()){
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.reset_color);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.reset_color);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.reset_color);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((currentQuestionPosition+1)+"/"+quesLists.size());
            questionAge5.setText(quesLists.get(currentQuestionPosition).getQuestion());
            option1.setText(quesLists.get(currentQuestionPosition).getOption1());
            option2.setText(quesLists.get(currentQuestionPosition).getOption2());
            option3.setText(quesLists.get(currentQuestionPosition).getOption3());

        } else{
            Intent intent = new Intent(quiz_age_5.this, scoreboard.class);
            intent.putExtra("correct", getCorrectAns());
            intent.putExtra("incorrect", getWrongAns());
            startActivity(intent);

            finish();
        }
    }
    public void startTimer(TextView time){
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(timeinMinute==1){
                    timeinMinute--;
                    second = 55;
                }
                else if (second==0 && timeinMinute==0){
                    //cancel time
                    timer.purge();
                    timer.cancel();

                    //set intent if time out to scoreboard
                    Intent intent = new Intent(quiz_age_5.this, scoreboard.class);
                    intent.putExtra("correct", getCorrectAns());
                    intent.putExtra("incorrect", getWrongAns());
                    startActivity(intent);

                }else {
                    second--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(timeinMinute);
                        String finalSeconds = String.valueOf(second);

                        if(finalMinutes.length() == 1){
                            finalMinutes = "0"+finalMinutes;
                        }

                        if(finalSeconds.length() == 1){
                            finalSeconds = "0" + finalSeconds;
                        }

                        time.setText(finalMinutes +":"+finalSeconds);
                    }
                });

            }
        }, 1000, 1000);
    }

    public int getCorrectAns(){
        int correctAns=0;

        for (int i=0; i<quesLists.size(); i++){

            final String getUserSelectAns = quesLists.get(i).getUserSelectAns();
            final String getAnswer = quesLists.get(i).getAnswer();

            if (getUserSelectAns.equals(getAnswer)){
                correctAns++;
            }
        }

       return correctAns;
    }


    public int getWrongAns(){
        int correctAns=0;

        for (int i=0; i<quesLists.size(); i++){

            final String getUserSelectAns = quesLists.get(i).getUserSelectAns();
            final String getAnswer = quesLists.get(i).getAnswer();

            if (!getUserSelectAns.equals(getAnswer)){
               correctAns++;
            }
        }
        return correctAns;
    }

    public void showAns(){
    final String getAnswer = quesLists.get(currentQuestionPosition).getAnswer();

        if(option1.getText().equals(getAnswer)){
        option1.setBackgroundResource(R.drawable.selected_answer_green);
        option1.setTextColor(Color.WHITE);
        }
        else if (option2.getText().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.selected_answer_green);
            option2.setTextColor(Color.WHITE);
        }
        else if (option3.getText().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.selected_answer_green);
            option3.setTextColor(Color.WHITE);
        }
    }

   @Override
   //when click back button, it will destroy all the db
    public void onBackPressed(){
       timer.purge();
       timer.cancel();
        finish();
   }
}