package com.example.quizappmyproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questiontext;
    private RadioGroup answersRadioGroup;
    private Button next_Button;
    private List<Question> questions;
    private int currentQuestionindex = 0;
    private int score = 0;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        questiontext = findViewById(R.id.questionText);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        next_Button = findViewById(R.id.next_Button);

        questions = QuestionData.getQuestions();
        loadQuestion(currentQuestionindex);

        next_Button.setOnClickListener(v->{
            checkAnswer();
            currentQuestionindex++;
            if (currentQuestionindex < questions.size()) {
                loadQuestion(currentQuestionindex);
                if(currentQuestionindex == questions.size()-1){
                    next_Button.setText("SUBMIT");
                }
            }
            else {
                displayScore();
            }
        });
    }

    private void loadQuestion(int questionIndex) {
        Question question = questions.get(questionIndex);
        questiontext.setText(question.getQuestionText());
        String[] options = question.getoptions();

        ((RadioButton) answersRadioGroup.getChildAt(0)).setText(options[0]);
        ((RadioButton) answersRadioGroup.getChildAt(1)).setText(options[1]);
        ((RadioButton) answersRadioGroup.getChildAt(2)).setText(options[2]);
        ((RadioButton) answersRadioGroup.getChildAt(3)).setText(options[3]);

        answersRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedId = answersRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            int selectedIndex = answersRadioGroup.indexOfChild(selectedRadioButton);
            Question question = questions.get(currentQuestionindex);
            if (selectedIndex == question.getCorrectchoiceindex()) {
                score++;
            }
        }
    }

    private void displayScore() {

        Intent i = new Intent(QuizActivity.this , ScoreActivity.class);
        i.putExtra("score",score);
        i.putExtra("TOTAL_QUESTIONS", questions.size());
        i.putExtra("username" , username);
        startActivity(i);
        finish();
  Toast.makeText(this, "Scored Displayed ", Toast.LENGTH_LONG).show();
    }
}
