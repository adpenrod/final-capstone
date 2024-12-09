package com.techelevator.model;

public class UserBadgeDto {
    private int userId;
    private String attractionType;
    private String badgeName;

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getAttractionType(){
        return attractionType;
    }

    public void setAttractionType(String attractionType){
        this.attractionType = attractionType;
    }

    public String getBadgeName(){
        return badgeName;
    }

    public void setBadgeName(String badgeName){
        this.badgeName = badgeName;
    }

    @Override
    public String toString() {
        return "UserBadgeDto{" +
                "userId=" + userId +
                ", attractionType='" + attractionType + '\'' +
                ", badgeName='" + badgeName + '\'' +
                '}';
    }
}
