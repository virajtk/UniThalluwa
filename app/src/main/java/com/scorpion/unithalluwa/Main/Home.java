package com.scorpion.unithalluwa.Main;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scorpion.unithalluwa.User_UI.UserLogin;

public class Home extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null ){
            startActivity(new Intent(getApplicationContext(), MainUI.class));
        }
    }
}
