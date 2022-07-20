package com.example.csprojedeneme;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private int xp;
    private String realName;
    private String challange1;
    private String challange2;
    private String challange3;
    private ArrayList<String> friendList;
    private int plansDone;
    private int challengesWon;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.xp = 0;
        challange1 = "";
        challange2 = "";
        challange3 = "";
        friendList = new ArrayList<>();
        plansDone = 0;
        challengesWon = 0;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getChallange1() {
        return challange1;
    }

    public void setChallange1(String challange1) {
        this.challange1 = challange1;
    }

    public String getChallange2() {
        return challange2;
    }

    public void setChallange2(String challange2) {
        this.challange2 = challange2;
    }

    public String getChallange3() {
        return challange3;
    }

    public void setChallange3(String challange3) {
        this.challange3 = challange3;
    }

    public ArrayList<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<String> friendList) {
        this.friendList = friendList;
    }

    public int getPlansDone() {
        return plansDone;
    }

    public void setPlansDone(int plansDone) {
        this.plansDone = plansDone;
    }

    public int getChallengesWon() {
        return challengesWon;
    }

    public void setChallengesWon(int challengesWon) {
        this.challengesWon = challengesWon;
    }
}