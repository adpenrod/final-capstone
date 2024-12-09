package com.techelevator.dao;

public interface UserBadgeDao {

    public boolean hasVistedAttractionTypeFiveTimes(int userId, String attractionType);
    public int getBadgeIdByName(String name);
    public void insertUserBadge(int userId, int badgeId);
    public void checkAndAwardBadge(int userId, String attractionType, String badgeName);
    public boolean hasVisitedEveryAttraction(int userId);

}
