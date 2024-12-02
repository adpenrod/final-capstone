package com.techelevator.dao;

import com.techelevator.model.Attraction;

import java.util.List;

public interface AttractionDao {

    public List<Attraction> getAttractions();
    public Attraction getAttractionById();
    public Attraction getAttractionByName();
    public Attraction getAttractionByAddress();
    public Attraction getAttractionByType();
    public Attraction getAttractionByHoursOfOperation();
}
