package com.techelevator.dao;

import com.techelevator.model.Type;

import java.util.List;

public interface TypeDao {
    public List<Type> getTypes();
    public Type getTypeById(int id);
    public Type getTypeByName(String name);
    public String getTypeByAttractionId(int id);
    public Type updateType(Type type);
    public Type createType(Type type);
    public int deleteTypeById(int id);
}
