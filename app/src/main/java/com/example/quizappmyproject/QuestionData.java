package com.example.quizappmyproject;
import java.util.ArrayList;
import java.util.List;

    public class QuestionData {
        public static List<Question> getQuestions() {
            List<Question> questions = new ArrayList<>();
            questions.add(new Question("1.What is a keyword in Java?",
                    new String[]{"if", "Integer", "switch", "None of the above"}, 0));

            questions.add(new Question("2.Which data type is used to create a variable that should store text?",
                    new String[]{"String", "char", "int", "float"}, 0));

            questions.add(new Question("3.Which of the following is not a feature of Java?",
                    new String[]{"Object-oriented", "Use of pointers", "Platform independent", "None of the above"}, 1));

            questions.add(new Question("4.What is the keyword used to declare a class in Java?" ,
                    new String[]{"Final" , "extends" , "new" , "class"},3));

            questions.add(new Question("5.Which keyword is used to inherit properties of Super Class(Base class) in Java?" ,
                    new String[]{"Final" , "extends" , "new" , "class"},1));

            questions.add(new Question("6.What is the name of the Java feature that ensures a program can run in multiple threads?" ,
                    new String[]{"Robust" , "extends" , "MultiThreading" , "Polymorphism"},2));

            questions.add(new Question("7.What data type should you use to store a true or false value?" ,
                    new String[]{"int" , "boolean" , "char" , "None of the above"},1));












            return questions;
        }
    }

