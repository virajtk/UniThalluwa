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

        Spinner  mySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RetriveAssignment.this,
                android.R.layout.activity_list_item,getResources().getStringArray(R.array.DropDownBox));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);
    }
}
