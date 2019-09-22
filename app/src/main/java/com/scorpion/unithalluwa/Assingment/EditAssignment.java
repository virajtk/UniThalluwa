package com.scorpion.unithalluwa.Assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scorpion.unithalluwa.R;

public class EditAssignment extends AppCompatActivity {

    Button editAssBtn,deleteAssBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        editAssBtn = findViewById(R.id.editAssBtn);
        deleteAssBtn = findViewById(R.id.deletebtn);

        editAssBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //update code


            }
        });

        deleteAssBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //delete code

            }
        });

    }
}


