package com.scorpion.unithalluwa.Assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.scorpion.unithalluwa.R;

public class AddAssignment extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        Spinner spinner1 = findViewById(R.id.spinner3);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        Spinner spinner2= findViewById(R.id.spinner4);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semester));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        Spinner spinner3 = findViewById(R.id.spinner6);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.module));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter2);
    }
}
