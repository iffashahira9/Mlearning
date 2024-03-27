package com.example.m_learning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class categoriesTeacherAge5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_teacher_age5);

        CardView cardAnimals5 = findViewById(R.id.cardAnimals5);
        CardView cardBodyPart5 = findViewById(R.id.cardBodyPart5);
        ImageView backButton = findViewById(R.id.backBtnTopic);

        backButton.setOnClickListener(v -> onBackPressed());
        cardAnimals5.setOnClickListener(v -> loadQuiz("animals5"));
        cardBodyPart5.setOnClickListener(v -> loadQuiz("bodyParts5"));
    }

    private void loadQuiz(String type) {
        Intent intent = new Intent(this, ManageQuiz.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}