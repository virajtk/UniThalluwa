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
import com.scorpion.unithalluwa.data.model.Marks;

public class ManageMarks extends AppCompatActivity {
    private Button reportbtn;
    private Button addbtn;
    private Button deletebtn;
    private Button updatebtn;
    Marks marks;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    String g1,g2,g3,g4,g5;

    private EditText txtMark1,txtMark2,txtMark3,txtMark4,txtMark5;
    private DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_marks);


        addbtn = findViewById(R.id.addbtn);
        reportbtn = findViewById(R.id.reportbtn);
        updatebtn = findViewById(R.id.updateResultBtn);
        deletebtn = findViewById(R.id.deletebtn);
        txtMark1 = findViewById(R.id.mark1);
        txtMark2 = findViewById(R.id.mark2);
        txtMark3 = findViewById(R.id.mark3);
        txtMark4 = findViewById(R.id.mark4);
        txtMark5 = findViewById(R.id.mark5);


        marks = new Marks();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("Marks");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(firebaseUser.getUid())){
                            try {
                                marks.setMark1(txtMark1.getText().toString().trim());
                                marks.setMark2(txtMark2.getText().toString().trim());
                                marks.setMark3(txtMark3.getText().toString().trim());
                                marks.setMark4(txtMark4.getText().toString().trim());
                                marks.setMark5(txtMark5.getText().toString().trim());
                                //clearControls();
                                dbref = FirebaseDatabase.getInstance().getReference().child("Marks").child(firebaseUser.getUid());
                                dbref.setValue(marks);

                                Toast.makeText(getApplicationContext(), "Marks Updated Successfully!", Toast.LENGTH_SHORT).show();

                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Invalied marks!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"First enter your Marks", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddResultActivity.class);
                startActivity(i);
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference deleteref = FirebaseDatabase.getInstance().getReference().child("Marks");
                deleteref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(firebaseUser.getUid())){
                            dbref =FirebaseDatabase.getInstance().getReference().child("Marks").child(firebaseUser.getUid());
                            dbref.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Marks deleted successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"First add your Marks!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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

                    g1 = txtMark1.getText().toString();
                    g2 = txtMark2.getText().toString();
                    g3 = txtMark3.getText().toString();
                    g4 = txtMark4.getText().toString();
                    g5 = txtMark5.getText().toString();
                }
                else
                    Toast.makeText(getApplicationContext(),"First add Your marks",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ManageMarks.this,ReportActivity.class);
                i.putExtra("mark1",g1);
                i.putExtra("mark2",g2);
                i.putExtra("mark3",g3);
                i.putExtra("mark4",g4);
                i.putExtra("mark5",g5);
                startActivity(i);
            }
        });

    }

    public void clearControls(){

        txtMark1.setText(null);
        txtMark2.setText(null);
        txtMark3.setText(null);
        txtMark4.setText(null);
        txtMark5.setText(null);
    }


}
