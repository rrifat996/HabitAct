package com.example.csprojedeneme;

public class Challenge {
    private String challengeName;
    private String creatorId;
    private String creatorName;
    private String description;
    private String id;
    private int creatorProgress;
    private int meeterProgress;
    private boolean meeted;
    private boolean completed;
    private int mImageResource;

    public Challenge(String challengeName, String creatorId, String description) {
        this.challengeName = challengeName;
        this.creatorId = creatorId;
        this.description = description;
        this.meeted = true;
        this.completed = false;
        this.creatorProgress = 0;
        this.meeterProgress = 0;
        //       this.creatorName = creatorName;
    }

    public Challenge(){}

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


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCreatorProgress() {
        return creatorProgress;
    }

    public void setCreatorProgress(int creatorProgress) {
        this.creatorProgress = creatorProgress;
    }

    public int getMeeterProgress() {
        return meeterProgress;
    }

    public void setMeeterProgress(int meeterProgress) {
        this.meeterProgress = meeterProgress;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public boolean isMeeted() {
        return meeted;
    }

    public void setMeeted(boolean meeted) {
        this.meeted = meeted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

