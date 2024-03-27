package com.example.m_learning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class categoriesTeacherAge6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_teacher_age6);

        CardView cardAnimals6 = findViewById(R.id.cardAnimals6);
        CardView cardBodyPart6 = findViewById(R.id.cardBodyPart6);
        ImageView backButton = findViewById(R.id.backBtnTopic);

        backButton.setOnClickListener(v -> onBackPressed());
        cardAnimals6.setOnClickListener(v -> loadQuiz("animals6"));
        cardBodyPart6.setOnClickListener(v -> loadQuiz("bodyParts6"));
    }

    private void loadQuiz(String type) {
        Intent intent = new Intent(this, ManageQuiz.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}