package com.example.splashdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static  final  int SPLASH_DELAY_MILLIS =1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            // Check if the user is already authenticated
            if (isUserAuthenticated()) {
                // User is authenticated, navigate to the main activity
                startActivity(new Intent(this, MainActivity.class));
            } else {
                // User is not authenticated, navigate to the login activity
                startActivity(new Intent(this, SignInActivity.class));
            }
        // Finish the splash activity to prevent returning to it when pressing the back button
            finish();
        },SPLASH_DELAY_MILLIS);



    }

    private boolean isUserAuthenticated() {
        // Check SharedPreferences for the login state
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean loggedIn = sharedPreferences.getBoolean("loggedIn", false);

        // Check Firebase Authentication state
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Return true if the user is authenticated either via SharedPreferences or Firebase Authentication
        return loggedIn || (currentUser != null);
    }
}