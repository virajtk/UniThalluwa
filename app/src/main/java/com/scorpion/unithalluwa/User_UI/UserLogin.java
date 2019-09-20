package com.scorpion.unithalluwa.User_UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.scorpion.unithalluwa.R;

public class UserLogin extends AppCompatActivity {

    Button loginBtn, signupBtn;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        txtUsername = findViewById(R.id.etUserName);
        txtPassword = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.registerBtn);



    }
}
