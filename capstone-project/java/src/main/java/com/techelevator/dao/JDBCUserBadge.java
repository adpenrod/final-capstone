package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCUserBadge implements UserBadgeDao{
    private final JdbcTemplate jdbcTemplate;

    public JDBCUserBadge(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean hasVistedAttractionTypeFiveTimes(int userId, String attractionType) {
        String sql = "SELECT COUNT(c.checkin_id) FROM checkin c JOIN attraction a " +
                "ON c.attraction_id = a.attraction_id JOIN type t ON a.type_id = t.type_id" +
                " WHERE c.user_id = ? AND t.name = ? ";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, attractionType);
            if (rowSet.next()) {
                return rowSet.getInt(1) >= 5;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return false;
    }

    @Override
    public int getBadgeIdByName(String name) {
        String sql = "SELECT badge_id FROM badge WHERE name = ?";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, name);
            if(rowSet.next()){
                return rowSet.getInt("badge_id");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return -1;
    }

    @Override
    public void insertUserBadge(int userId, int badgeId) {

        String sql = "INSERT INTO user_badge (user_id, badge_id, earned_time) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP)";
        try{
            jdbcTemplate.update(sql, userId, badgeId);
        } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException ed){
            throw new DaoException("Data integrity Violation", ed);
        }

    }

    @Override
    public void checkAndAwardBadge(int userId, String attractionType, String badgeName) {
        if(hasVistedAttractionTypeFiveTimes(userId, attractionType)){
            int badgeId = getBadgeIdByName(badgeName);

            if(badgeId != -1){
                insertUserBadge(userId, badgeId);
            } else {
                System.out.println("Badge with name " + badgeName + " was not found.");
            }
        }  else {
            System.out.println("User with id " + userId + " has not visited " + attractionType + " five times.");

        }

    }
}
