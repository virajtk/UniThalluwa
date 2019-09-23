package com.scorpion.unithalluwa.Assingment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scorpion.unithalluwa.R;

public class userAssignment extends AppCompatActivity {

    Button addABtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_assignment);

        addABtn = findViewById(R.id.MyAsBtn);

        addABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),RetriveAssignment.class);
                startActivity(i);
            }
        });
    }

}

