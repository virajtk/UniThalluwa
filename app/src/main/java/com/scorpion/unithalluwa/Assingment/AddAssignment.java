package com.scorpion.unithalluwa.Assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.Assignment;

public class AddAssignment extends AppCompatActivity {

    DatabaseReference dbref;
    Spinner spinner1, spinner2, spinner3;
    EditText etTopic;
    Button btnAdd;
    Assignment std;

    private void clearContrals(){

        spinner1.setSelection(0);
        spinner2.setSelection(0);
        spinner3.setSelection(0);
        etTopic.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        std = new Assignment();
        spinner1 = findViewById(R.id.yearSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        spinner2= findViewById(R.id.SemSpinner);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semester));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        spinner3 = findViewById(R.id.ModuleSpinner);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.module));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter2);

        etTopic = findViewById(R.id.topic);
        btnAdd = findViewById(R.id.addBtn);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spinner1 = findViewById(R.id.yearSpinner);
                spinner2 = findViewById(R.id.SemSpinner);
                spinner3 = findViewById(R.id.ModuleSpinner);
                etTopic = findViewById(R.id.topic);
                btnAdd = findViewById(R.id.addBtn);

                dbref = FirebaseDatabase.getInstance().getReference().child(" Add Assignmet  ");

                try {
                    if (TextUtils.isEmpty(spinner1.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(),"  Select Year... ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(spinner2.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), " Select Semester...  ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(spinner3.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "   Select Module...  ",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(etTopic.getText().toString() ))
                        Toast.makeText(getApplicationContext(), "  Select Topic...   ",Toast.LENGTH_SHORT).show();

                    else{
                        std.setYear(spinner1.getSelectedItem().toString().trim());
                        std.setSem(spinner2.getSelectedItem().toString().trim());
                        std.setModule(spinner3.getSelectedItem().toString().trim());
                        std.setAssTitle(etTopic.getText().toString().trim());


                        dbref.push().setValue(std);

                        Toast.makeText(getApplicationContext(), " New Assignment Add successfully    ",Toast.LENGTH_SHORT).show();
                        clearContrals();
                    }

                }
catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"   ", Toast.LENGTH_SHORT).show();

}

            }
        });

    }
}
