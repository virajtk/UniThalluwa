package com.scorpion.unithalluwa.Mark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;

public class ManageMarks extends AppCompatActivity {
    private Button reportbtn;
    private Button addbtn;
    private Button deletebtn;
    private Button updatebtn;

    private EditText txtMark1,txtMark2,txtMark3,txtMark4,txtMark5;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_marks);


        addbtn = findViewById(R.id.addbtn);
        reportbtn = findViewById(R.id.reportbtn);
        txtMark1 = findViewById(R.id.mark1);
        txtMark2 = findViewById(R.id.mark2);
        txtMark3 = findViewById(R.id.mark3);
        txtMark4 = findViewById(R.id.mark4);
        txtMark5 = findViewById(R.id.mark5);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddResultActivity.class);
                startActivity(i);
            }
        });


        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ReportActivity.class);
                startActivity(i);
            }
        });

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        dbref = FirebaseDatabase.getInstance().getReference().child("Marks").child(firebaseUser.getUid());

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    txtMark1.setText(dataSnapshot.child("mark1").getValue().toString());
                    txtMark2.setText(dataSnapshot.child("mark2").getValue().toString());
                    txtMark3.setText(dataSnapshot.child("mark3").getValue().toString());
                    txtMark4.setText(dataSnapshot.child("mark4").getValue().toString());
                    txtMark5.setText(dataSnapshot.child("mark5").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"First add Your marks",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
