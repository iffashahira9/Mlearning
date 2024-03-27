package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeTeacher extends AppCompatActivity {

    CardView manageQuiz,logout;
    FirebaseAuth mlearning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);

        manageQuiz =findViewById(R.id.manageQuiz);
        logout = findViewById(R.id.logout);

        mlearning=FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlearning.signOut();
                startActivity(new Intent(HomeTeacher.this, loginTeacher.class));
                finish();

            }
        });
        manageQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeTeacher.this, ChooseAgeTeacher.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
    Toast.makeText(HomeTeacher.this, "To logout, press Logout button", Toast.LENGTH_SHORT).show();
    }
}