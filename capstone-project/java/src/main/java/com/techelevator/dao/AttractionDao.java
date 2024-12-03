package com.techelevator.dao;

import com.techelevator.model.Attraction;


import java.util.List;

public interface AttractionDao {

    public List<Attraction> getAttractions();
    public Attraction getAttractionById(int id);
    public Attraction getAttractionByName(String name);
    public Attraction getAttractionByAddress(String address);
    public Attraction getAttractionByType(String typeName);
    public Attraction getAttractionByIsOpen(boolean isOpen);
    public Attraction createAttraction(Attraction attraction);
    public int deleteAttractionById(int id);
    public Attraction updateAttraction(Attraction attraction);
}
