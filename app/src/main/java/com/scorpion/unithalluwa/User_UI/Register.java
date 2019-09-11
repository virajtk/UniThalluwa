package com.scorpion.unithalluwa.User_UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.ui.login.LoginActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner roleSpinner = (Spinner) findViewById(R.id.roleDropbox);


        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(Register.this , R.array.roleItems, android.R.layout.simple_spinner_item);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(roleAdapter);


        Button regButton = findViewById(R.id.regbtn);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
