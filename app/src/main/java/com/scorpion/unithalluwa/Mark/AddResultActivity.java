package com.scorpion.unithalluwa.Mark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.scorpion.unithalluwa.R;

public class AddResultActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        Spinner spinner = findViewById(R.id.spinner3);
       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReport();
            }
        });

    }

    public void openReport(){
        Intent intent = new Intent(this,ReportActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text1 = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text1, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
