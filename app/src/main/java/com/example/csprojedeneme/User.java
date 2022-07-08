package com.example.csprojedeneme;

public class User {
    private String username;
    private String password;
    private int index;

    public User(String username, String password, int index) {
        this.username = username;
        this.password = password;
        this.index = index;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int index(){
        return index;
    }
}
