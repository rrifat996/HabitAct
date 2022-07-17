package com.example.csprojedeneme;

public class User {
    private String username;
    private String password;
    private int xp;
    private String realName;
    private String[] lastChallengesIds;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.xp = 0;
        lastChallengesIds = new String[3];
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getXp(){
        return xp;
    }
    public void setRealName(String str){
        realName = str;
    }
    public String getRealName(){
        return realName;
    }

    public String[] getLastChallengesIds() {
        return lastChallengesIds;
    }

    public void setLastChallengesIds(String[] lastChallengesIds) {
        this.lastChallengesIds = lastChallengesIds;
    }
}