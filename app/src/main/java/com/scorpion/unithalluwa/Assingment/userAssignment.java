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
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.Assignment;

import java.util.ArrayList;

public class userAssignment extends AppCompatActivity {

    Button addABtn;
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
        setContentView(R.layout.activity_user_assignment);

        recyclerView = findViewById(R.id.recyclerviewUserAss);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<Assignment>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Assignment").child(firebaseUser.getUid());//
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
                        Intent intent = new Intent(userAssignment.this, RetriveAssignment.class);
//                        intent.putExtra("assTitle",model.getAssTitle());
//                        intent.putExtra("year",model.getYear());
//                        intent.putExtra("sem",model.getSem());
//                        intent.putExtra("module",model.getModule());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FirebaseViewHolderAssignment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolderAssignment(LayoutInflater.from(userAssignment.this).inflate(R.layout.row_assignment,parent,false));
            }
        };


        recyclerView.setAdapter(adapter);







        addABtn = findViewById(R.id.MyAsBtn);

        addABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),RetriveAssignment.class);
                startActivity(i);
            }
        });
    }

}

