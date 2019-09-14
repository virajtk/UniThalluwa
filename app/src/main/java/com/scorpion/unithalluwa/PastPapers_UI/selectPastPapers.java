package com.scorpion.unithalluwa.PastPapers_UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.User_UI.Register;

public class selectPastPapers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_past_papers);

        Spinner spinner1 = findViewById(R.id.yearSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(selectPastPapers.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

//        final Spinner yearspinner = (Spinner) findViewById(R.id.yearSpinner);
//
//        ArrayAdapter<CharSequence> roleAdapter
//                = ArrayAdapter.createFromResource(selectPastPapers.this , R.array.year,
//                android.R.layout.simple_spinner_item);
//        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        yearspinner.setAdapter(roleAdapter);


    }
}
