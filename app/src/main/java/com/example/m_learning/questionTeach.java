package com.example.m_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m_learning.databinding.ActivityMainBinding;
import com.example.m_learning.databinding.ActivityQuestionTeachBinding;
import com.google.common.net.InternetDomainName;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class questionTeach extends AppCompatActivity {
ActivityQuestionTeachBinding binding;
Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuestionTeachBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cancel=findViewById(R.id.cancel);
        final String getTopicName = getIntent().getStringExtra("Topic");

        binding.updateQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question = binding.question.getText().toString();
                String option1 = binding.option1.getText().toString();
                String option2 = binding.option2.getText().toString();
                String option3 = binding.option3.getText().toString();
                String answer = binding.answer.getText().toString();
                QuesList quesList = new QuesList(question,option1,option2,option3,answer);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(getTopicName);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent
                finish();
            }
        });
    }
}