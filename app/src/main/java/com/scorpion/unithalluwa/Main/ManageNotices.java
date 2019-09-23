package com.scorpion.unithalluwa.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.scorpion.unithalluwa.R;

public class ManageNotices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notices);

        String title = getIntent().getStringExtra("title");
        String message = getIntent().getStringExtra("message");
        String sender = getIntent().getStringExtra("sender");
        Log.i("OUR VALUE",title);
        Log.i("OUR VALUE 2",message);
        Log.i("OUR VALUE 3",sender);
        Toast.makeText(this, ""+title, Toast.LENGTH_SHORT).show();
    }
}
