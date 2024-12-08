package com.techelevator.model;

import java.time.LocalDateTime;

public class UserBadge {

    int userId;
    int badgeId;
    private LocalDateTime checkinTime = LocalDateTime.now();


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public UserBadge(int userId, int badgeId, LocalDateTime checkinTime) {
        this.userId = userId;
        this.badgeId = badgeId;
        this.checkinTime = checkinTime;
    }

    public UserBadge(){

    }
}
