package com.example.m_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageQuiz extends AppCompatActivity {
    Button button;
    QuestionAdapter adapter = new QuestionAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_quiz);

        RecyclerView recyclerView = findViewById(R.id.quizQuestions);
        button = findViewById(R.id.addButton);
        findViewById(R.id.backButton).setOnClickListener(v -> onBackPressed());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (getIntent().hasExtra("type")) {
            loadQuiz(getIntent().getStringExtra("type"));
        } else {
            throw new IllegalArgumentException("Missing quiz type");
        }
    }

    @Override
    protected void onPause() {
        if (adapter.getItemCount() < 3) {
            Toast.makeText(this, "Minimum question needed is three", Toast.LENGTH_LONG).show();
        } else {
            super.onPause();
        }
    }

    private void loadQuiz(String type) {
        String quiz = "";

        switch (type) {
            case "animals5":
                quiz = "Animals";
                break;
            case "animals6":
                quiz = "AnimalsAge6";
                break;
            case "bodyParts5":
                quiz = "BodyParts";
                break;
            case "bodyParts6":
                quiz = "BodyPartsAge6";
                break;
        }

        Intent intent = new Intent(this, AddEditQuiz.class);
        intent.putExtra("type", quiz);
        button.setOnClickListener(view -> startActivity(intent));

        FirebaseDatabase.getInstance().getReference(quiz).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.removeAll();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Question question = data.getValue(Question.class);

                    if (question != null) {
                        question.setId(data.getKey());
                        question.setQuiz(snapshot.getKey());
                        adapter.add(question);
                    }
                }

                if (adapter.getItemCount() > 6) {
                    button.setVisibility(View.GONE);
                } else {
                    button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}