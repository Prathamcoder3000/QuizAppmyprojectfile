package com.example.quizappmyproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginbutton;
    TextView signupredirect, textforgot;
    UserDatabaseManager dbhelper;
    String email,gender;

    // Member variables to hold values passed from MainActivity
    String userFromMain, emailFromMain, genderFromMain, passwordFromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        dbhelper = new UserDatabaseManager(this);
        dbhelper.open();

        loginUsername = findViewById(R.id.emaillogin);
        loginPassword = findViewById(R.id.passlogin);

        loginbutton = findViewById(R.id.buttonlogin);
        signupredirect = findViewById(R.id.registerredirect);
        textforgot = findViewById(R.id.text_forgot);

        loginbutton.setOnClickListener(v -> loginUser());

        Intent intent2 = getIntent();
        email = intent2.getStringExtra("email");
        gender = intent2.getStringExtra("gender");

        textforgot.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
            startActivity(intent);
        });

        signupredirect.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(intent);
        });

        // Retrieve values passed from MainActivity

    }

    private void loginUser() {
        String user = loginUsername.getText().toString();
        String pass = loginPassword.getText().toString();

        String[] credentials = dbhelper.getUserCredentials(user);

        if (credentials == null || credentials.length == 0) {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        } else if (pass.equals(credentials[1])) { // Check password only since username is already validated
            Toast.makeText(this, "User is successfully logged in!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
            intent.putExtra("username", user);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbhelper.close();
    }
}