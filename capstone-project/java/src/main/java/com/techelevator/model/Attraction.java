package com.techelevator.model;

import java.math.BigDecimal;

public class Attraction {

    private int id;
    private String name;
    private String description;
    private String hoursOfOperation;
    private String address;
    private String image;
    private String socialMedia;
    private int typeId;
    private BigDecimal latitude;
    private BigDecimal longitude;

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

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Attraction() {}




    public Attraction(int id, String name, String description, String hoursOfOperation, String address, String image, String socialMedia, int typeId, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hoursOfOperation = hoursOfOperation;
        this.address = address;
        this.image = image;
        this.socialMedia = socialMedia;
        this.typeId = typeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
