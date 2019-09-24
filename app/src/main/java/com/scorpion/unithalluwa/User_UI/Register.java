package com.scorpion.unithalluwa.User_UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.User;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText txtUserName, txtRegNumber , txtPassword , txtEmail , txtConfirmPassword;
    Button registerBtn , resetBtn , signInBtn;
    CheckBox acceptRulesCheckBox;
    User user;
    DatabaseReference dbRef;
    boolean existUser;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    //method to clear all user inputs
    public void resetFields(){

        txtUserName.setText(null);
        txtRegNumber.setText(null);
        txtEmail.setText(null);
        txtPassword.setText(null);
        txtConfirmPassword.setText(null);
        acceptRulesCheckBox.setChecked(false);

    }

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._]{1,100}" +
                    "@"+
                    "[a-zA-Z0-9]{0,10}" +
                    "." +
                    "[a-zA-Z]{0,5}"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = new User();

        final Spinner roleSpinner = findViewById(R.id.roleDropbox);
        ArrayAdapter<CharSequence> roleAdapter
                = ArrayAdapter.createFromResource(Register.this , R.array.roleItems,
                android.R.layout.simple_spinner_item);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(roleAdapter);


        //Casting Views
        txtUserName = findViewById(R.id.etUserName);
        txtRegNumber = findViewById(R.id.etRegNumber);
        txtPassword = findViewById(R.id.etPassword);
        txtConfirmPassword = findViewById(R.id.confirmPassword);
        txtEmail = findViewById(R.id.etEmail);
        progressBar = findViewById(R.id.progressBar);
        acceptRulesCheckBox = findViewById(R.id.checkBoxRules);

        registerBtn = findViewById(R.id.regbtn);
        resetBtn = findViewById(R.id.resetbtn);
        signInBtn = findViewById(R.id.signinBtn);

        firebaseAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("User");




//        txtUserName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                dbRef = FirebaseDatabase.getInstance().getReference().child("User");
//                dbRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        existUser = dataSnapshot.hasChild(txtUserName.getText().toString().trim());
////                        String msg = String.valueOf(existUser);
////                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//        });




        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String password = txtPassword.getText().toString();
                String confirmPass = txtConfirmPassword.getText().toString();

                //take inputs from the user and assign them to user object
                user.setUserName(txtUserName.getText().toString().trim());
                user.setRegNumber(txtRegNumber.getText().toString().trim());
                user.setEmail(txtEmail.getText().toString().trim());
                user.setRole(roleSpinner.getSelectedItem().toString());

//                try {

//
//                    //data validation
//                    if (existUser){
//                        String message = "username : "+txtUserName.getText().toString().trim()+" already exists";
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                        txtUserName.setText(null);
//                    }
//                    else {
//
                        if (TextUtils.isEmpty(user.getUserName())){
                            Toast.makeText(getApplicationContext(), "Please enter user name", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(user.getRegNumber())) {
                            Toast.makeText(getApplicationContext(), "Please enter Registration Number", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(password)) {
                            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(confirmPass)) {
                            Toast.makeText(getApplicationContext(), "Please re-enter password", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(user.getEmail())) {
                            Toast.makeText(getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
                        } else if (!password.equals(confirmPass)) {
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                            txtConfirmPassword.setText(null);
                        }else if (!acceptRulesCheckBox.isChecked()){
                            Toast.makeText(getApplicationContext(), "Please Accept Rules and Regulations", Toast.LENGTH_SHORT).show();
                        }
                        //do the Registration
                        else {
                            progressBar.setVisibility(View.VISIBLE);

                            firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),password).addOnCompleteListener
                                    (new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            progressBar.setVisibility(View.GONE);

                                            if(task.isSuccessful()){

                                                //Insert in to the database
                                                dbRef.child(FirebaseAuth.getInstance().getCurrentUser()
                                                        .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(getApplicationContext(),
                                                                "Registration Complete", Toast.LENGTH_LONG).show();

                                                        resetFields();

                                                        Intent i = new Intent(getApplicationContext(), UserLogin.class);
                                                        startActivity(i);
                                                        finish();
                                                    }
                                                });

                                            } else {
                                                Toast.makeText(getApplicationContext(),
                                                        task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });

                        }
//                    }

//                }catch (NumberFormatException e){
//                    Toast.makeText(getApplicationContext(),"Invalid data !" , Toast.LENGTH_SHORT).show();
//                }

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetFields();

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(i);
            }
        });
    }

}
