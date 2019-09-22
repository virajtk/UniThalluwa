package com.scorpion.unithalluwa.Assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.scorpion.unithalluwa.R;

public class RetriveAssignment extends AppCompatActivity {

    //create a new object for the button
    Button addBtn;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_assignment);

        //assign it to what we want
        addBtn = findViewById(R.id.AddNewAsBtn);
        spinner1 = findViewById(R.id.spinner5);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semester));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        Spinner spinner3 = findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.module));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter2);

        //click event
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),AddAssignment.class);
                startActivity(i);
            }
        });
    }

}
