package com.scorpion.unithalluwa.User_UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;

public class profile extends AppCompatActivity {

    EditText profileUsername, profileEmail, profileRegNo, profileRole;
    Button logoutBtn, updateBtn,deleteUserBtn,editBtn,cancelBtn;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference readReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileUsername = findViewById(R.id.etUserName);
        profileEmail = findViewById(R.id.etEmail);
        profileRegNo = findViewById(R.id.etRegNumber);
        profileRole = findViewById(R.id.etRole);
        logoutBtn = findViewById(R.id.removeBtn);
        editBtn = findViewById(R.id.editBtn);
        cancelBtn = findViewById(R.id.cancelBtn);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        readReference = FirebaseDatabase.getInstance().getReference()
                .child("User").child(firebaseUser.getUid());
        readReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    profileUsername.setText(dataSnapshot.child("userName").getValue().toString());
                    profileEmail.setText(dataSnapshot.child("email").getValue().toString());
                    profileRegNo.setText(dataSnapshot.child("regNumber").getValue().toString());
                    profileRole.setText(dataSnapshot.child("role").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileUsername.setEnabled(true);
                profileRegNo.setEnabled(true);
                profileRole.setEnabled(true);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileUsername.setEnabled(false);
                profileRegNo.setEnabled(false);
                profileRole.setEnabled(false);
            }
        });
    }
}
