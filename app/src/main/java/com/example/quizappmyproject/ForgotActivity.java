package com.example.quizappmyproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotActivity extends AppCompatActivity {
    EditText forgotUsername, newPassword, confirmPassword;
    Button resetPasswordButton;
    UserDatabaseManager dbhelper;
    TextView logintext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_activity);

        dbhelper = new UserDatabaseManager(this);
        dbhelper.open();

        forgotUsername = findViewById(R.id.usernamenew);
        newPassword = findViewById(R.id.passnew);
        confirmPassword = findViewById(R.id.pass2new);
        resetPasswordButton = findViewById(R.id.buttonnew);
        logintext = findViewById(R.id.forgotidnew);

        resetPasswordButton.setOnClickListener(v -> {
            String username = forgotUsername.getText().toString();
            String newPass = newPassword.getText().toString();
            String confirmPass = confirmPassword.getText().toString();

            if (username.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                String storedPassword = dbhelper.getPassword(username);
                if (storedPassword != null) {
                    dbhelper.updatePassword(username, newPass);
                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logintext.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbhelper.close();
    }
}
