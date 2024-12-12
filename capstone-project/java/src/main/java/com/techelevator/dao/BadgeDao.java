package com.techelevator.dao;

import com.techelevator.model.Attraction;
import com.techelevator.model.Badge;

import java.util.List;

public interface BadgeDao {
    public List<Badge> getUserBadges(int id);
    public Badge getBadgeById(int id);
    public Badge getBadgeByName(String name);
    public Badge getBadgeByTypeId(int typeId);
    public String getBadgeNameByCheckIn(int Id);
    public Badge createBadge(Badge Badge);
    public int deleteBadgeById(int id);
    public Badge updateBadge(Badge badge);
}
