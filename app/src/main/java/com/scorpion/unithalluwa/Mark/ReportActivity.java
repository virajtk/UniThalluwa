package com.scorpion.unithalluwa.Mark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;
import java.lang.String;

public class ReportActivity extends AppCompatActivity {

    TextView gpa,regNo,userName;
    FirebaseAuth firebaseAuth;
    DatabaseReference dbRef;

    private double answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        gpa = findViewById(R.id.textViewAnswer);
        regNo = findViewById(R.id.textViewRegNo);
        userName = findViewById(R.id.textViewUserName);

        Intent i = getIntent();
        String mark1  = i.getStringExtra("mark1");
        String mark2  = i.getStringExtra("mark2");
        String mark3  = i.getStringExtra("mark3");
        String mark4  = i.getStringExtra("mark4");
        String mark5  = i.getStringExtra("mark5");

        answer = (calGpa(mark1) + calGpa(mark2) + calGpa(mark3) + calGpa(mark4) + calGpa(mark5))/5;
        gpa.setText(Double.toString(answer));


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid());

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    regNo.setText(dataSnapshot.child("regNumber").getValue().toString());
                    userName.setText(dataSnapshot.child("userName").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"No user",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private double calGpa(String Grade){
        if (Grade.equals("A") | Grade.equals("A+")){
             return 4.0;
        }
        else if (Grade.equals("A-") ){
            return 3.7;
        }
        else if (Grade.equals("B+") ){
            return 3.3;
        }
        else if (Grade.equals("B")){
            return 3.0;
        }
        else if (Grade.equals("B-")){
            return 2.7;
        }
        else if (Grade.equals("C+")){
            return 2.3;
        }
        else if (Grade.equals("C") ){
            return 2.0;
        }
        else if (Grade.equals("C-") ){
            return 1.7;
        }
        else if (Grade.equals("D+") ){
            return 1.3;
        }
        else if (Grade.equals("D") ){
            return 1.0;
        }
        else if (Grade.equals("D-") ){
            return 0.7;
        }
        else{
            return 0.0;
        }
    }
}
