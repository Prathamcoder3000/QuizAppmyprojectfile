package com.example.quizappmyproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserActivity extends AppCompatActivity {
    UserDatabaseManager dbhelper;
    Button registerbutton;
    TextView goToLoginButton; // Added button for navigating to LoginActivity
    EditText username, email, pass, pass2;
    CheckBox termscondition;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        dbhelper = new UserDatabaseManager(this);
        dbhelper.open();


        // Start AsyncTask //only we have to run not for storing
        new FetchDataTask().execute();

        registerbutton = findViewById(R.id.button);
        goToLoginButton = findViewById(R.id.loginbutton); // Initialize go to login button
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        pass2 = findViewById(R.id.pass2);
        termscondition = findViewById(R.id.checkBox);

        registerbutton.setOnClickListener(v -> registerUser());
        goToLoginButton.setOnClickListener(v -> goToLogin()); // Set OnClickListener for go to login button
    }
    private class FetchDataTask extends AsyncTask<Void, Integer, String> {


        @Override
        protected String doInBackground(Void... voids) { //unlimited parameters
            // Perform background computation
            try {
                // Simulate network request
                Thread.sleep(2000); // Simulate delay
                return "Data fetched";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void registerUser() {
        String user = username.getText().toString();
        String emailAdd = email.getText().toString();
        String password = pass.getText().toString();
        String password2 = pass2.getText().toString();

        RadioGroup radio = findViewById(R.id.radioItem);
        int genderid = radio.getCheckedRadioButtonId();
        String genderstr = "";
        if (genderid != -1) {
            RadioButton gender = findViewById(genderid);
            genderstr = gender.getText().toString();
        }

        if (user.isEmpty() || emailAdd.isEmpty() || password.isEmpty() || password2.isEmpty() || genderstr.isEmpty() || !termscondition.isChecked()) {
            Toast.makeText(this, "Invalid Entry or Enter Details again", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(password2)) {
            Toast.makeText(this, "Password not matched or rewrite it", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "USER IS SUCCESSFULLY REGISTERED!", Toast.LENGTH_SHORT).show();
            dbhelper.insertUser(user, emailAdd, password, genderstr);

            Intent i = new Intent(UserActivity.this, LoginActivity.class);
            i.putExtra("username", user);
            i.putExtra("email", emailAdd);
            i.putExtra("password", password);
            i.putExtra("gender", genderstr);
            startActivity(i);


        }
    }


    private void goToLogin() {
        Intent intent = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbhelper.close();
    }
}