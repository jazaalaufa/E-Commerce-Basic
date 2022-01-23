package com.example.finalprojects2;

public class User {
    public String fullname, no, email, password;

    public User(){

    }
    public User(String fullname, String no, String email, String password){
        this.fullname = fullname;
        this.no = no;
        this.email = email;
        this.password = password;
    }


    public String getFullname() {
        return fullname;
    }

    public String getNo() {
        return no;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
