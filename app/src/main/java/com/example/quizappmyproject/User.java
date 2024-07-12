package com.example.quizappmyproject;

public class User {

    private int id;
    private String Username;
    private String name;
    private String email;
    private String password;
    private String gender;

    User(int id , String Username , String name , String email , String password , String gender){
        this.id = id;
        this.Username = Username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;

    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String uname){
        this.Username = Username;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    //getters
    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.Username;
    }

    public String getName(){
        return  this.name;
    }


    public String getEmail(){
        return  this.email;
    }

    public  String getPassword(){
        return  this.password;
    }

    public String getGender(){
        return  this.gender;
    }



}

