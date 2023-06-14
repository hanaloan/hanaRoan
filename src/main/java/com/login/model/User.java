package com.login.model;

public class User {
    private String username;
    private String password;

    // Getter와 Setter 생략

    public User(String username, String password) {
        this.username = username;
        this.password = password;


    }


    public String getPassword() {

        return password;
    }

    public String getUsername() {
        return username;
    }
}