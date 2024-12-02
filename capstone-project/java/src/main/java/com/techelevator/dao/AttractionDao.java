package com.techelevator.dao;

import com.techelevator.model.Attraction;

import java.util.List;

public interface AttractionDao {

    public List<Attraction> getAttractions();
    public Attraction getAttractionById(int id);
    public Attraction getAttractionByName(String name);
    public Attraction getAttractionByAddress(String address);
    public Attraction getAttractionByType(int typeId);
    public Attraction getAttractionByIsOpen(boolean isOpen);
}
