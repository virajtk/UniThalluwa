package com.scorpion.unithalluwa.Assingment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
import com.scorpion.unithalluwa.data.model.Assignment;

public class EditAssignment extends AppCompatActivity {

    Button editAssBtn,deleteAssBtn;
    Assignment assignment;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference dBRef;

    private EditText txtModule,txtSem,txtYear,txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        txtModule = findViewById(R.id.txtModule);
        txtSem = findViewById(R.id.txtSem);
        txtYear = findViewById(R.id.txtYear);
        txtTitle = findViewById(R.id.txtTitle);

        editAssBtn = findViewById(R.id.editAssBtn);
        deleteAssBtn = findViewById(R.id.deletebtn);

        assignment = new Assignment();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        String assTitle = getIntent().getStringExtra("assTitle");
        String year = getIntent().getStringExtra("year");
        String sem = getIntent().getStringExtra("sem");
        String module = getIntent().getStringExtra("module");

        Toast.makeText(this, ""+assTitle, Toast.LENGTH_SHORT).show();

        assignment.setAssTitle(assTitle);
        assignment.setYear(year);
        assignment.setSem(sem);
        assignment.setModule(module);

        txtTitle.setText(assignment.getAssTitle());
        txtYear.setText(assignment.getYear());
        txtSem.setText(assignment.getSem());
        txtModule.setText(assignment.getModule());

        //update code!
        editAssBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();

                DatabaseReference updateReference = FirebaseDatabase.getInstance().getReference().child("Assignment").child(firebaseUser.getUid());
                updateReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(firebaseUser.getUid())){
                            try{

                                assignment.setAssTitle(txtTitle.getText().toString().trim());
                                assignment.setModule(txtModule.getText().toString().trim());
                                assignment.setSem(txtSem.getText().toString().trim());
                                assignment.setYear(txtYear.getText().toString().trim());

                               dBRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(firebaseUser.getUid());
                               dBRef.setValue(assignment);
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Inavlied Assignment!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Enter an assignment",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

        //delete code
        deleteAssBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Assignment");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(firebaseUser.getUid())){
                            dBRef = FirebaseDatabase.getInstance().getReference().child("Assignment").child(firebaseUser.getUid());
                            delRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Assignment deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"First add an Assignment!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}


