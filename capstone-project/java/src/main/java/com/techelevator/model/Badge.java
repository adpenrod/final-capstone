package com.techelevator.model;

public class Badge {
    private int id;
    private String name;
    private String description;
    private String lockedImage;
    private String unlockedImage;
    private Boolean unlocked;
    private String criteria;

    public String getLockedImage() {
        return lockedImage;
    }

    public void setLockedImage(String lockedImage) {
        this.lockedImage = lockedImage;
    }

    public String getUnlockedImage() {
        return unlockedImage;
    }

    public void setUnlockedImage(String unlockedImage) {
        this.unlockedImage = unlockedImage;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Badge(int id, String name, String description, String lockedImage, String unlockedImage, Boolean unlocked, String criteria) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lockedImage = lockedImage;
        this.unlockedImage = unlockedImage;
        this.unlocked = unlocked;
        this.criteria = criteria;
    }

    public Badge(){

    }

}
