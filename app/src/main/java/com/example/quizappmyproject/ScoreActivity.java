package com.example.quizappmyproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private UserDatabaseManager dbhelper;
    private TextView scoretext , text2;
    private Button restartbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

//     String username = getIntent().getStringExtra("username");
//        int score = getIntent().getIntExtra("SCORE", 0);
//        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        int score = intent.getIntExtra("score" , 0);
        int totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS" , 0);

        scoretext = findViewById(R.id.scoretext);
        restartbutton = findViewById(R.id.restartbutton);
        text2 = findViewById(R.id.text2);
        scoretext.setText(username+"\n Score is : - "+ score + " / "+ totalQuestions);
        text2.setText("Thank you for playing our Quiz on our Quizto Application , "+username);

        restartbutton.setOnClickListener(v->{
            Intent i = new Intent(ScoreActivity.this , LoginActivity.class);
            startActivity(i);

        });

        dbhelper = new UserDatabaseManager(this);
    }
}
