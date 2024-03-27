package com.example.m_learning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ChooseAgeTeacher extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_age_teacher);

        CardView cardAge5 = findViewById(R.id.cardAge5);
        CardView cardAge6 = findViewById(R.id.cardAge6);
        ImageView backBtnTopic = findViewById(R.id.backBtnTopic);

        backBtnTopic.setOnClickListener(v -> onBackPressed());
        cardAge5.setOnClickListener(v -> startActivity(new Intent(this, categoriesTeacherAge5.class)));
        cardAge6.setOnClickListener(v -> startActivity(new Intent(this, categoriesTeacherAge6.class)));
    }
}