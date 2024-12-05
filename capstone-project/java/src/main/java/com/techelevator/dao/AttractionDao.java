package com.techelevator.dao;

import com.techelevator.model.Attraction;
import org.w3c.dom.Attr;


import java.math.BigDecimal;
import java.util.List;

public interface AttractionDao {

    public List<Attraction> getAttractions();
    public Attraction getAttractionById(int id);
    public List<Attraction> getAttractionByName(String name);
    public List <Attraction> getAttractionByAddress(String address);
    public List<Attraction> getAttractionByType(String typeName);
    public Attraction createAttraction(Attraction attraction);
    public int deleteAttractionById(int id);
    public Attraction updateAttraction(Attraction attraction);
    public BigDecimal getLatitude(int id);
    public BigDecimal getLongitude(int id);
}
