package com.example.splashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText login_email;
    private EditText login_pass;
    private TextView create_acc_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        login_email = findViewById(R.id.log_email);
        login_pass = findViewById(R.id.log_pass);
        create_acc_text = findViewById(R.id.create_account);

        create_acc_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        // Handle sign-in button click
//        Button signInButton = findViewById(R.id.signInButton);
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Perform user sign-in (as shown in the previous response)
//                String email = login_email.getText().toString();
//                String password = login_pass.getText().toString();
//                // Sign in the user
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // User signed in successfully
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    if (user != null) {
//                                        String userId = user.getUid();
//                                        // Now you have the user's ID (userId)
//                                    }
//                                } else {
//                                    // Sign-in failed, handle the error
//                                    Exception exception = task.getException();
//                                    // Handle the error (e.g., show an error message)
//                                    Toast.makeText(getApplicationContext(),"Something went wrong.",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//            }
//        });
    }

    private void signIn() {
        String email = login_email.getText().toString();
        String password = login_pass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-In success
//                        FirebaseUser user = mAuth.getCurrentUser();
                        // Handle success, e.g., navigate to the main activity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        // Sign-up failed
                        // Handle failure, e.g., show an error message
                        Toast.makeText(getApplicationContext(),"Couldn't LogIn",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
