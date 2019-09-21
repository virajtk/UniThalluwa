package com.scorpion.unithalluwa.PastPapers_UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scorpion.unithalluwa.R;

public class download_papersAndAnswers extends AppCompatActivity {
    private static  final int MY_PERMISSION = 0;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_papers_and_answers);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if(Build)

            }
        });
    }
}
