package com.scorpion.unithalluwa.Assingment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.unithalluwa.Main.DataSetFire;
import com.scorpion.unithalluwa.Main.FirebaseViewHolder;
import com.scorpion.unithalluwa.Main.MainUI;
import com.scorpion.unithalluwa.Main.ManageNotices;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.Assignment;

import java.util.ArrayList;

public class RetriveAssignment extends AppCompatActivity {

    //create a new object for the button
    Button addBtn;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private RecyclerView recyclerView;
    private ArrayList<Assignment> arrayList;
    private FirebaseRecyclerOptions<Assignment> options;
    private FirebaseRecyclerAdapter<Assignment, FirebaseViewHolderAssignment> adapter;
    private DatabaseReference databaseReference;


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_assignment);

        recyclerView = findViewById(R.id.recyclerviewAss);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<Assignment>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Assignment").child(firebaseUser.getUid());
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<Assignment>().setQuery(databaseReference,Assignment.class).build();

        adapter = new FirebaseRecyclerAdapter<Assignment, FirebaseViewHolderAssignment>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolderAssignment holder, int position, @NonNull final Assignment model) {
                holder.assTitle.setText(model.getAssTitle());
                holder.year.setText(model.getYear());
                holder.sem.setText(model.getSem());
                holder.module.setText(model.getModule());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RetriveAssignment.this, EditAssignment.class);
                        intent.putExtra("assTitle",model.getAssTitle());
                        intent.putExtra("year",model.getYear());
                        intent.putExtra("sem",model.getSem());
                        intent.putExtra("module",model.getModule());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FirebaseViewHolderAssignment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolderAssignment(LayoutInflater.from(RetriveAssignment.this).inflate(R.layout.row_assignment,parent,false));
            }
        };


        recyclerView.setAdapter(adapter);





        //assign it to what we want
        addBtn = findViewById(R.id.AddNewAsBtn);

        //click event
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),AddAssignment.class);
                startActivity(i);
            }
        });
    }

}
