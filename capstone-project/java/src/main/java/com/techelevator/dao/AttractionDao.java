package com.techelevator.dao;

import com.techelevator.model.Attraction;
import org.w3c.dom.Attr;
import java.math.BigDecimal;
import java.util.List;

public interface AttractionDao {
    List<Attraction> getAttractions();
    Attraction getAttractionById(int id);
    List<Attraction> getAttractionByName(String name);
    List <Attraction> getAttractionByAddress(String address);
    List<Attraction> getAttractionByType(String typeName);
    Attraction createAttraction(Attraction attraction);
    int deleteAttractionById(int id);
    Attraction updateAttraction(Attraction attraction);
    BigDecimal getLatitude(int id);
    BigDecimal getLongitude(int id);
}
