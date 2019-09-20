package com.scorpion.unithalluwa.Mark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scorpion.unithalluwa.R;

public class ManageMarks extends AppCompatActivity {
    private Button reportbtn;
    private Button addbtn;
    private Button deletebtn;
    private Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_marks);


        addbtn =findViewById(R.id.addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddResultActivity.class);
                startActivity(i);
            }
        });

        reportbtn = findViewById(R.id.reportbtn);
        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ReportActivity.class);
                startActivity(i);
            }
        });
    }

}
