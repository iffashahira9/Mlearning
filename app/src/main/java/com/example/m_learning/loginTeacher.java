package com.example.m_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginTeacher extends AppCompatActivity {

    private TextView txtForgetPass;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView txtRegister;
   private Button btnLogin;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //db
    FirebaseAuth mlearning;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

    txtForgetPass =findViewById(R.id.txtForgetPass);
    loginEmail = findViewById(R.id.loginEmail);
    loginPassword = findViewById(R.id.loginPassword);
    btnLogin = findViewById(R.id.btnLogin);
    txtRegister=findViewById(R.id.txtRegister);

    //db
    mlearning=FirebaseAuth.getInstance();
    fstore=FirebaseFirestore.getInstance();

    if (mlearning.getUid() != null) {
        startActivity(new Intent(loginTeacher.this, HomeTeacher.class));
        finish();
    }






        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            loginUser();
        }
    });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginTeacher.this, register.class));
            }
        });

    }

    private void loginUser(){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            loginEmail.setError("Email cannot be empty");
            loginEmail.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Please provide valid email");
            loginEmail.requestFocus();
            return;
        }
        if (password.length()<6) {
            loginPassword.setError("password length must be at least 6 characters");
            loginPassword.requestFocus();
            return;

        } else {
            mlearning.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(loginTeacher.this, "User logged in successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginTeacher.this, HomeTeacher.class));
                    } else {
                        Toast.makeText(loginTeacher.this, "Log in error!: "
                                + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {


        startActivity(new Intent(loginTeacher.this,MainActivity.class));
        finish();
    }



}