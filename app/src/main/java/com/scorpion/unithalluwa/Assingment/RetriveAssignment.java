package com.scorpion.unithalluwa.Assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.scorpion.unithalluwa.R;

public class RetriveAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_assignment);

        Spinner spinner1 = findViewById(R.id.spinner5);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semester));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        Spinner spinner3 = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.module));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter2);
    }
}
