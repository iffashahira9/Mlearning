package com.example.m_learning;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AddEditQuiz extends AppCompatActivity {
    private EditText etAnswer;
    private EditText etOption1;
    private EditText etOption2;
    private EditText etOption3;
    private EditText etQuestion;
    private Question question;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_quiz);

        etAnswer = findViewById(R.id.questionAddAnswer);
        etOption1 = findViewById(R.id.questionAddOption1);
        etOption2 = findViewById(R.id.questionAddOption2);
        etOption3 = findViewById(R.id.questionAddOption3);
        etQuestion = findViewById(R.id.questionAddQuestion);
        findViewById(R.id.backButton).setOnClickListener(v -> onBackPressed());

        if (getIntent().hasExtra("type")) {
            type = getIntent().getStringExtra("type");
        } else {
            throw new IllegalArgumentException();
        }

        if (getIntent().hasExtra("question")) {
            ((TextView) findViewById(R.id.titleBar)).setText("Edit question");
            question = new Gson().fromJson(getIntent().getStringExtra("question"), Question.class);
        }

        processQuiz();
    }

    private void processQuiz() {
        if (question != null) {
            etAnswer.setText(question.getAnswer());
            etOption1.setText(question.getOption1());
            etOption2.setText(question.getOption2());
            etOption3.setText(question.getOption3());
            etQuestion.setText(question.getQuestion());
        } else {
            question = new Question();
        }

        findViewById(R.id.saveButton).setOnClickListener(v -> {
            // Validate EditText fields are not empty
            if (etAnswer.getText().toString().isEmpty() ||
                    etOption1.getText().toString().isEmpty() ||
                    etOption2.getText().toString().isEmpty() ||
                    etOption3.getText().toString().isEmpty() ||
                    etQuestion.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                return;
            }

        findViewById(R.id.saveButton).setOnClickListener(view -> {
            question.setAnswer(etAnswer.getText().toString());
            question.setOption1(etOption1.getText().toString());
            question.setOption2(etOption2.getText().toString());
            question.setOption3(etOption3.getText().toString());
            question.setQuestion(etQuestion.getText().toString());


            if (question.getId() == null) {
                FirebaseDatabase.getInstance()
                        .getReference(type)
                        .push()
                        .setValue(question)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Question added", Toast.LENGTH_LONG).show();
                            finish();
                        });
            } else {
                FirebaseDatabase.getInstance()
                        .getReference(question.getQuiz())
                        .child(question.getId())
                        .setValue(question)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Question edited", Toast.LENGTH_LONG).show();
                            finish();
                        });
            }
            });
        });
    }
}