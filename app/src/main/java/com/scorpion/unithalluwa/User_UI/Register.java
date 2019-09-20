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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.User;
import com.scorpion.unithalluwa.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText txtUserName, txtRegNumber , txtPassword , txtEmail , txtConfirmPassword;
    Button registerBtn , resetBtn , signInBtn;
    User user;
    DatabaseReference dbRef;
    boolean existUser;

    //method to clear all user inputs
    public void resetFields(){

        txtUserName.setText(null);
        txtRegNumber.setText(null);
        txtEmail.setText(null);
        txtPassword.setText(null);
        txtConfirmPassword.setText(null);

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

        final Spinner roleSpinner = (Spinner) findViewById(R.id.roleDropbox);

        ArrayAdapter<CharSequence> roleAdapter
                = ArrayAdapter.createFromResource(Register.this , R.array.roleItems,
                android.R.layout.simple_spinner_item);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(roleAdapter);


//        dbRef = FirebaseDatabase.getInstance().getReference().child("User");
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                childCount = dataSnapshot.getChildrenCount();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        txtUserName = findViewById(R.id.etUserName);
        txtRegNumber = findViewById(R.id.etRegNumber);
        txtPassword = findViewById(R.id.etPassword);
        txtConfirmPassword = findViewById(R.id.confirmPassword);
        txtEmail = findViewById(R.id.etEmail);


        txtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("User");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        existUser = dataSnapshot.hasChild(txtUserName.getText().toString().trim());
                        String msg = String.valueOf(existUser);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        registerBtn = findViewById(R.id.regbtn);
        resetBtn = findViewById(R.id.resetbtn);
        signInBtn = findViewById(R.id.signinBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    dbRef = FirebaseDatabase.getInstance().getReference().child("User");

                    //data validation
                    if (existUser){
                        String message = "username : "+txtUserName.getText().toString().trim()+" already exists";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        txtUserName.setText(null);
                    }
                    else {

                        if (TextUtils.isEmpty(txtUserName.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Please enter user name", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                            txtConfirmPassword.setText(null);
                        } else if (TextUtils.isEmpty(txtRegNumber.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Please enter Registration Number", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                            txtConfirmPassword.setText(null);
                        } else if (TextUtils.isEmpty(txtPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                            txtConfirmPassword.setText(null);
                        } else if (TextUtils.isEmpty(txtConfirmPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Please re-enter password", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                        } else if (TextUtils.isEmpty(txtEmail.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                            txtConfirmPassword.setText(null);
                        } else if (!txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                            txtConfirmPassword.setText(null);
                        } else if (!EMAIL_ADDRESS_PATTERN.matcher(txtEmail.getText().toString().trim()).matches()) {
                            Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                            txtPassword.setText(null);
                            txtConfirmPassword.setText(null);
                            txtEmail.setText(null);
                        }

                        //do the Registration
                        else {

                            //take inputs from the user and assign them to user object
                            user.setUserName(txtUserName.getText().toString().trim());
                            user.setRegNumber(txtRegNumber.getText().toString().trim());
                            user.setPassword(txtPassword.getText().toString().trim());
                            user.setEmail(txtEmail.getText().toString().trim());
                            user.setRole(roleSpinner.getSelectedItem().toString());

                            //Insert in to the database
                            // dbRef.push().setValue(user);
//                        String nextID = String.valueOf(childCount+1);
//                        String finalID = null;
//                        if(nextID.length()==1){
//                            finalID = "U00"+nextID;
//                        }
//                        else if(nextID.length() == 2){
//                            finalID = "U0"+nextID;
//                        }
//                        else if(nextID.length() == 3){
//                            finalID = "U"+nextID;
//                        }

                            dbRef.child(user.getUserName()).setValue(user);

                            Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                            resetFields();

                        }
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid data !" , Toast.LENGTH_SHORT).show();
                }




//                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(i);
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
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

}
