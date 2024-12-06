package com.techelevator.model;

public class Badge {
    private int id;
    private String name;
    private String description;
    private String image;
    private String criteria;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Badge(int id, String name, String description, String image, String criteria) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.criteria = criteria;
    }
    public Badge(){

    }

}
