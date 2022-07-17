package com.example.csprojedeneme;

public class Challenge {
    private String challengeName;
    private String creatorId;
    private String description;
    private boolean isActive;

    public Challenge(String challengeName, String creatorId, String description) {
        this.challengeName = challengeName;
        this.creatorId = creatorId;
        this.description = description;
        this.isActive = true;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

