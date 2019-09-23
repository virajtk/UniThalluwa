package com.scorpion.unithalluwa.About;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.scorpion.unithalluwa.Main.MainUI;
import com.scorpion.unithalluwa.R;

public class Info extends AppCompatActivity {

    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        home = findViewById(R.id.imageView13);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainUI.class);
                startActivity(i);
                finish();
            }
        });
    }
}
