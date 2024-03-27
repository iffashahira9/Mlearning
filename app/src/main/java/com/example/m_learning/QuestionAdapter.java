package com.example.m_learning;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private final List<Question> questions = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Question question = questions.get(position);
        viewHolder.answer.setText(question.getAnswer());
        viewHolder.option1.setText(question.getOption1());
        viewHolder.option2.setText(question.getOption2());
        viewHolder.option3.setText(question.getOption3());
        viewHolder.question.setText(question.getQuestion());

        viewHolder.edit.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddEditQuiz.class);
            intent.putExtra("type", question.getQuiz());
            intent.putExtra("question", new Gson().toJson(question));
            view.getContext().startActivity(intent);
        });

        viewHolder.remove.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Are you sure to delete this question?");
            builder.setNegativeButton("Cancel", null);
            builder.setPositiveButton("Confirm", (dialogInterface, i) -> {
                FirebaseDatabase.getInstance().getReference(question.getQuiz()).child(question.getId()).removeValue();
                remove(position);
            });
            builder.show();
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_question, viewGroup, false
        ));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void add(Question question) {
        questions.add(question);
        notifyItemInserted(questions.indexOf(question));
    }

    public void remove(int position) {
        questions.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAll() {
        int position = 0;

        if (questions.size() > 0) {
            remove(position);
            removeAll();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button edit;
        Button remove;
        TextView answer;
        TextView option1;
        TextView option2;
        TextView option3;
        TextView question;

        ViewHolder(View view) {
            super(view);
            answer = view.findViewById(R.id.questionAnswer);
            edit = view.findViewById(R.id.questionButtonEdit);
            option1 = view.findViewById(R.id.questionOption1);
            option2 = view.findViewById(R.id.questionOption2);
            option3 = view.findViewById(R.id.questionOption3);
            question = view.findViewById(R.id.questionQuestion);
            remove = view.findViewById(R.id.questionButtonRemove);
        }
    }
}