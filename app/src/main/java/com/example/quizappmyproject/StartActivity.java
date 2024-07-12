package com.example.quizappmyproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private UserDatabaseManager dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        TextView textuser = findViewById(R.id.text_user);
        textuser.setText("Welcome \n\n to \n\n the \n\n Quizto \n\n "+username);

        Button buttonnew = findViewById(R.id.buttonnew);
        buttonnew.setOnClickListener(v->{
            Intent intent2 = new Intent(StartActivity.this , QuizActivity.class);
            intent2.putExtra("username" , username);
            startActivity(intent2);
        });


        dbhelper = new UserDatabaseManager(this);
    }
}
