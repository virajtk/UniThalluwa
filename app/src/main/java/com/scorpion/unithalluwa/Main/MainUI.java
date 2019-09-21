package com.scorpion.unithalluwa.Main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.unithalluwa.Assingment.AddAssignment;
import com.scorpion.unithalluwa.Assingment.RetriveAssignment;
import com.scorpion.unithalluwa.Mark.AddCAMarks;
import com.scorpion.unithalluwa.Mark.ManageMarks;
import com.scorpion.unithalluwa.PastPapers_UI.viewPastPapers;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.User_UI.UserLogin;
import com.scorpion.unithalluwa.User_UI.profile;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class MainUI extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView userEmail,currentUsername;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference readReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), profile.class);
                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                userEmail = findViewById(R.id.currentUserEmail);
                currentUsername = findViewById(R.id.mainUIUsername);

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();

                readReference = FirebaseDatabase.getInstance().getReference()
                        .child("User").child(firebaseUser.getUid());
                readReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            currentUsername.setText(dataSnapshot.child("userName").getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                userEmail.setText(firebaseUser.getEmail());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(getApplicationContext(), UserLogin.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else if (id == R.id.action_AppInfo) {
            //Intent i = new Intent(getApplicationContext(), Info.class);
            //startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent i = new Intent(getApplicationContext(),profile.class);
            startActivity(i);
        } else if (id == R.id.nav_mark) {
            Intent i = new Intent(getApplicationContext(), ManageMarks.class);
            startActivity(i);
        } else if (id == R.id.nav_markingSchema) {
            Intent i = new Intent(getApplicationContext(), viewPastPapers.class);
            startActivity(i);
        } else if (id == R.id.nav_assignment) {
            Intent i = new Intent(getApplicationContext(), RetriveAssignment.class);
            startActivity(i);
        } else if (id == R.id.nav_GPA) {
            //Intent i = new Intent(getApplicationContext(),#.class);
            //startActivity(i);
        } else if (id == R.id.nav_req) {
            //Intent i = new Intent(getApplicationContext(),#.class);
            //startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
