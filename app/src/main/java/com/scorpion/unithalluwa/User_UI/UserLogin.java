package com.scorpion.unithalluwa.User_UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.scorpion.unithalluwa.Main.MainUI;
import com.scorpion.unithalluwa.R;

public class UserLogin extends AppCompatActivity {

    Button loginBtn, signUpBtn;
    EditText txtUsername, txtPassword;
    TextView mRecoverPassTv;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //Connect xml & Java files
        txtUsername = findViewById(R.id.etEmail);
        txtPassword = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.registerBtn);
        mRecoverPassTv = findViewById(R.id.textViewForgotPass);
        progressBar = findViewById(R.id.loginProgressBar);

        pd = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
                finish();
            }
        });

        //Login
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

        //Recover pass textView click
        mRecoverPassTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecoveryPasswordDialog();
            }
        });

    }

    private void showRecoveryPasswordDialog() {
        //AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");

        //set layout linear layout
        LinearLayout linearLayout = new LinearLayout(this);
        //views to set in dialog
        final EditText emailEt = new EditText(this);
        emailEt.setHint("Email");
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        emailEt.setMinEms(16);

        linearLayout.addView(emailEt);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        //buttons recover
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //input email
                String email = emailEt.getText().toString().trim();
                beginRecovery(email);
            }
        });
        //buttons cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //dismiss dialog
                dialogInterface.dismiss();
            }
        });

        //show dialog
        builder.create().show();

    }

    private void beginRecovery(String email) {
        //show progress dialog
        pd.setMessage("Sending Email...");
        pd.show();

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(UserLogin.this,"Recovery Email sent",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserLogin.this, "Failed..", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                //get and show proper error message
                Toast.makeText(UserLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
