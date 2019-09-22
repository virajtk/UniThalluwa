package com.scorpion.unithalluwa.Mark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.Marks;

public class AddResultActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText txtMark1,txtMark2,txtMark3,txtMark4,txtMark5;
    private DatabaseReference dbref;
    Marks marks;
    private Button addresultbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        txtMark1 = findViewById(R.id.mark1);
        txtMark2 = findViewById(R.id.mark2);
        txtMark3 = findViewById(R.id.mark3);
        txtMark4 = findViewById(R.id.mark4);
        txtMark5 = findViewById(R.id.mark5);

        addresultbtn = findViewById(R.id.addresultbtn);

        marks = new Marks();

        addresultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Marks");
                try {
                    if (TextUtils.isEmpty(txtMark1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Marks For sub1", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtMark1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Marks For sub2", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtMark1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Marks For sub3", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtMark1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Marks For sub4", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtMark1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Marks For sub5", Toast.LENGTH_SHORT).show();
                    else {
                        marks.setMark1(txtMark1.getText().toString().trim());
                        marks.setMark2(txtMark2.getText().toString().trim());
                        marks.setMark3(txtMark3.getText().toString().trim());
                        marks.setMark4(txtMark4.getText().toString().trim());
                        marks.setMark5(txtMark5.getText().toString().trim());

                        //dbref.push().setValue(marks);
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        dbref.child(firebaseUser.getUid()).setValue(marks);
                        Toast.makeText(getApplicationContext(), "Marks added Successfully!", Toast.LENGTH_SHORT).show();
                        clearControls();
                        openMark();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct Marks", Toast.LENGTH_SHORT).show();
                }
            }
        });


       // Spinner spinner = findViewById(R.id.spinner3);
        // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner.setAdapter(adapter);
      //  spinner.setOnItemSelectedListener(this);


        addresultbtn = findViewById(R.id.addresultbtn);


    }

    public void openMark(){
        Intent intent = new Intent(this,ManageMarks.class);
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

    public void clearControls(){
        txtMark1.setText(null);
        txtMark2.setText(null);
        txtMark3.setText(null);
        txtMark4.setText(null);
        txtMark5.setText(null);
    }


}
