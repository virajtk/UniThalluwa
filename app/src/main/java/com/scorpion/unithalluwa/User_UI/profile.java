package com.scorpion.unithalluwa.User_UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.User;

public class profile extends AppCompatActivity {

    EditText profileUsername, profileEmail, profileRegNo, profileRole;
    Button updateBtn,deleteUserBtn,editBtn,cancelBtn,changePassBtn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference readReference;
    DatabaseReference dbRef;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = new User();

        profileUsername = findViewById(R.id.etUserName);
        profileEmail = findViewById(R.id.etEmail);
        profileRegNo = findViewById(R.id.etRegNumber);
        profileRole = findViewById(R.id.etRole);

        progressBar = findViewById(R.id.ProfileProgressBar);

        editBtn = findViewById(R.id.editBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        updateBtn = findViewById(R.id.updateButton);
        deleteUserBtn = findViewById(R.id.removeBtn);

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

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("User");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(firebaseUser.getUid())){
                                user.setUserName(profileUsername.getText().toString().trim());
                                user.setEmail(profileEmail.getText().toString().trim());
                                user.setRegNumber(profileRegNo.getText().toString().trim());
                                user.setRole(profileRole.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid());
                                dbRef.setValue(user);
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(profile.this,"Update Complete",Toast.LENGTH_SHORT).show();
                            profileUsername.setEnabled(false);
                            profileRegNo.setEnabled(false);
                            profileRole.setEnabled(false);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(profile.this);
                dialog.setTitle("Dude, Are you sure?");
                dialog.setMessage("Delete this account will result in completely removing your account " +
                        "from Uni Thalluwa and you won't be able to access the app or undone.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()){
                                    DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("User");
                                    deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.hasChild(firebaseUser.getUid())){
                                                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid());
                                                dbRef.removeValue();
                                                Toast.makeText(getApplicationContext(),"Account Removed",Toast.LENGTH_LONG).show();

                                                FirebaseAuth.getInstance().signOut();
                                                Intent i = new Intent(getApplicationContext(), UserLogin.class);
                                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(i);
                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(),"No source to delete",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else{
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });

                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });
    }
}
