package com.example.quizappmyproject;

public class Question {

    private String QuestionText;
    private String[] options;
    private int correctchoiceindex;

    public Question(String QuestionText , String[] options , int correctchoiceindex){
        this.QuestionText = QuestionText;
        this.options = options;
        this.correctchoiceindex = correctchoiceindex;
    }

    public String getQuestionText(){
        return QuestionText;
    }

    public  String[] getoptions(){
        return options;
    }

    public int getCorrectchoiceindex(){
        return correctchoiceindex;
    }
}
