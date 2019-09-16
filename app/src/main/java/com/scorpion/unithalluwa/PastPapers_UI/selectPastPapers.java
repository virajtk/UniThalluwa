package com.scorpion.unithalluwa.PastPapers_UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.User_UI.Register;

public class selectPastPapers extends AppCompatActivity {

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_past_papers);

        Spinner spinner1 = findViewById(R.id.yearSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(selectPastPapers.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        submitButton = findViewById(R.id.submitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),download_papersAndAnswers.class);
                startActivity(i);
            }
        });

    }
}
