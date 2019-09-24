package com.scorpion.unithalluwa.PastPapers_UI;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.PastPapers;

import org.w3c.dom.Text;

import static java.nio.file.AccessMode.READ;

public class editPastPapers extends AppCompatActivity {
    EditText yearOfthePaper, semester, moduleName;
    Button importBtn, updateBtn, dltBtn;
    DatabaseReference dbRef;
    PastPapers epp;




    public void clearControls() {
        yearOfthePaper.setText("");
        semester.setText("");
        moduleName.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_past_papers);



        yearOfthePaper = findViewById(R.id.yearOfthePaper);
        semester = findViewById(R.id.semester);

        importBtn = findViewById(R.id.importpapersBtn);
        updateBtn = findViewById(R.id.upBtn);
        dltBtn = findViewById(R.id.deleBtn);


        epp = new PastPapers();



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("editPastPapers");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("epp1")) {
                            try {
                                epp.setYears(yearOfthePaper.getText().toString().trim());
                                epp.setSemester(semester.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("editPastPapers").child("editPastPapers1");
                                dbRef.setValue(epp);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).
                                        show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                dltBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("editPastPapers");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("epp1")) {
                                    dbRef = FirebaseDatabase.getInstance().getReference().child("editPastPapers").child("epp1");
                                    dbRef.removeValue();
                                    clearControls();
                                    Toast.makeText(getApplicationContext(),
                                            "data deleted successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), " no source to delete", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });

                    }
                });
            }
        });
    }
}



