package com.scorpion.unithalluwa.User_UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.scorpion.unithalluwa.Main.MainUI;
import com.scorpion.unithalluwa.R;

public class UserLogin extends AppCompatActivity {

    Button loginBtn, signUpBtn;
    EditText txtUsername, txtPassword;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        txtUsername = findViewById(R.id.etEmail);
        txtPassword = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.loginProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtUsername.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(txtPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(txtUsername.getText().toString(),
                            txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                startActivity(new Intent(UserLogin.this, MainUI.class));
                                String welcomeMsg = "Welcome to UniThalluwa";
                                Toast.makeText(getApplicationContext(), welcomeMsg,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }

            }
        });



    }
}
