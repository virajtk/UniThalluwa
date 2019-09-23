package com.scorpion.unithalluwa.Mark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.scorpion.unithalluwa.R;

public class ReportActivity extends AppCompatActivity {

    TextView gpa;

    private double answer = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        gpa = findViewById(R.id.textViewAnswer);

        Intent i = getIntent();
        String mark1  = i.getStringExtra("mark1");
        String mark2  = i.getStringExtra("mark2");
        String mark3  = i.getStringExtra("mark3");
        String mark4  = i.getStringExtra("mark4");
        String mark5  = i.getStringExtra("mark5");

        answer = calGpa(mark1) + calGpa(mark2) + calGpa(mark3) + calGpa(mark4) + calGpa(mark5);
        gpa.setText(Double.toString(answer));


    }

    private double calGpa(String Grade){
        if (Grade == "A" || Grade == "A+"){
             return 4.0;
        }
        else if (Grade == "A-" ){
            return 3.7;
        }
        else if (Grade == "B+" ){
            return 3.3;
        }
        else if (Grade == "B" ){
            return 3.0;
        }
        else if (Grade == "B-" ){
            return 2.7;
        }
        else if (Grade == "C+" ){
            return 2.3;
        }
        else if (Grade == "C" ){
            return 2.0;
        }
        else if (Grade == "C-" ){
            return 1.7;
        }
        else if (Grade == "D+" ){
            return 1.3;
        }
        else if (Grade == "D" ){
            return 1.0;
        }
        else if (Grade == "D-" ){
            return 0.7;
        }
        else{
            return 0.0;
        }
    }
}
