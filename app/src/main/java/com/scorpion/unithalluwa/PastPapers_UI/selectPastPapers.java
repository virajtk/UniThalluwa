package com.scorpion.unithalluwa.PastPapers_UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.User_UI.Register;
import com.scorpion.unithalluwa.data.model.PastPapers;

import java.util.ArrayList;

public class selectPastPapers extends AppCompatActivity {

    Button submitButton;
    Spinner spinnerNew;
    DatabaseReference dbRef;
    PastPapers pastPapers;
    Spinner yearOfthePaper;
    EditText semester,moduleName;
    FirebaseAuth firebaseAuth;


    private void clearControls() {

        moduleName.setText("");
        spinnerNew.setSelection(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_past_papers);


        pastPapers = new PastPapers();
        spinnerNew = findViewById(R.id.yearSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(selectPastPapers.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNew.setAdapter(myAdapter);

        yearOfthePaper = findViewById(R.id.yearSpinner);
        semester = findViewById(R.id.etSem);
        moduleName = findViewById(R.id.etModule);

        submitButton = findViewById(R.id.submitBtn);

        pastPapers = new PastPapers();



        firebaseAuth = FirebaseAuth.getInstance();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //add pastpapers
                dbRef = FirebaseDatabase.getInstance().getReference().child("PastPapers");
                                                try {
                                                    if (TextUtils.isEmpty(yearOfthePaper.getSelectedItem().toString())) {
                                                        Toast.makeText(getApplicationContext(), "Please enter the year", Toast.LENGTH_SHORT).show();
                                                    } else if (TextUtils.isEmpty(semester.getText().toString())) {
                                                        Toast.makeText(getApplicationContext(), "Please enter the semester", Toast.LENGTH_SHORT).show();
                                                    } else if (TextUtils.isEmpty(moduleName.getText().toString())) {
                                                        Toast.makeText(getApplicationContext(), "Please enter the module name", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        pastPapers.setYears(spinnerNew.getSelectedItem().toString().trim());
                                                        pastPapers.setSemester(semester.getText().toString().trim());
                                                        pastPapers.setModule(moduleName.getText().toString().trim());

                                                        //dbRef.push().setValue(pastPapers);

                                                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();



                                                        dbRef.child(firebaseUser.getUid()).child(pastPapers.getModule()).setValue(pastPapers);

                                                        Toast.makeText(getApplicationContext(), "Data saved Successfully", Toast.LENGTH_SHORT).show();
                                                        clearControls();

                                                        Intent i = new Intent(getApplicationContext(),viewPastPapers.class);
                                                        startActivity(i);
                                                    }
                                                } catch (NumberFormatException e) {
                                                    Toast.makeText(getApplicationContext(), "Invalid year", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });

    }


}
