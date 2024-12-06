package com.techelevator.model;

import java.time.LocalDateTime;

public class Checkin {
    private int id;
    private int userId;
    private int attractionId;
    private LocalDateTime checkinTime;
    private String notes;

    public Checkin(int id) {
        this.id = id;
    }

    public Checkin(int id, int userId, int attractionId, LocalDateTime checkinTime, String notes) {
        this.id = id;
        this.userId = userId;
        this.attractionId = attractionId;
        this.checkinTime = checkinTime;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
