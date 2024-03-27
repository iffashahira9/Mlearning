package com.example.m_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private EditText regName,regEmail,regPass,regPass2;
    private Button btnRegister;

    FirebaseAuth mlearning;
    FirebaseFirestore fstore;

    public static final String TAG = "TAG";
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        regName = findViewById(R.id.regName);
        regPass = findViewById(R.id.regPass);
        regPass2= findViewById(R.id.regPass2);
        regEmail = findViewById(R.id.regEmail);

        mlearning= FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String email  = regEmail.getText().toString();
        String  pass1 = regPass.getText().toString();
        String pass2 = regPass2.getText().toString();
        String name = regName.getText().toString();

        if(TextUtils.isEmpty(email)){
            regEmail.setError("email cannot be empty");
            regEmail.requestFocus();
            return;

        }
        if (TextUtils.isEmpty(name)){
            regName.setError("name cannot be empty");
            regName.requestFocus();
            return;

        }
        if(TextUtils.isEmpty(pass1)){
            regPass.setError("password cannot be empty");
            regPass.requestFocus();
            return;

        }
        if(TextUtils.isEmpty(pass2)){
            regPass2.setError("password cannot be empty");
            regPass2.requestFocus();
            return;

        }
        if(!TextUtils.equals(pass1, pass2)){
            regPass2.setError("please insert password correctly");
            regPass2.requestFocus();
            return;

        } if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEmail.setError("Please provide valid email");
            regEmail.requestFocus();
            return;

        }
        if (regPass.length()<6) {
            regPass.setError("password length must be at least 6 characters");
            regPass.requestFocus();
            return;
        }else{
            mlearning.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(register.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        userId = mlearning.getCurrentUser().getUid();
                        DocumentReference documentReference = fstore.collection("users").document(userId);
                        Map<String,Object> user = new HashMap<>();
                        user.put("fullname", name);
                        user.put("email", email);
                        user.put("pass1", pass1);
                        user.put("pass2", pass2);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: user Profile is created for" + userId);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });
                        startActivity(new Intent(register.this, loginTeacher.class));
                    }
                    else{
                        Toast.makeText(register.this, "Registration error!: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}