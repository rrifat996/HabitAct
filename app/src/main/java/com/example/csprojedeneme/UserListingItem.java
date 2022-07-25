package com.example.csprojedeneme;

public class UserListingItem {
    private int challengesWon;
    private String username;
    private int xp;

    public UserListingItem(int challengesWon, String username, int xp) {
        this.challengesWon = challengesWon;
        this.username = username;
        this.xp = xp;
    }

    public int getChallengesWon() {
        return challengesWon;
    }

    public void setChallengesWon(int challengesWon) {
        this.challengesWon = challengesWon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
