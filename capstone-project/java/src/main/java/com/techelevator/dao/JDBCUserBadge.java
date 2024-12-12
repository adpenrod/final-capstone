package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Badge;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCUserBadge implements UserBadgeDao{
    private final JdbcTemplate jdbcTemplate;

    private final int FIVE_VISITS = 5;
    private final int MAX_VISITS = 25;
    private final int DEFENDER_BADGE = 1;

    public JDBCUserBadge(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean hasVistedAttractionTypeFiveTimes(int userId, String attractionType) {
        String sql = "SELECT COUNT(c.checkin_id) FROM checkin c JOIN attraction a " +
                "ON c.attraction_id = a.attraction_id JOIN type t ON a.type_id = t.type_id" +
                " WHERE c.user_id = ? AND t.name = ? ";

        int userAttractionTypeVisits = 0;

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, attractionType);
            if (rowSet.next()) {
                userAttractionTypeVisits = rowSet.getInt(1);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }finally {
            return userAttractionTypeVisits >= FIVE_VISITS;
        }
    }

    @Override
    public boolean hasVisitedEveryAttraction(int userId) {
        String sql = "SELECT \n" +
                "\t(\n" +
                "\t\tSELECT COUNT(DISTINCT(attraction_id)) FROM attraction\n" +
                "\n" +
                "\t) - COUNT(DISTINCT(c.attraction_id))\n" +
                "FROM \n" +
                "\tcheckin c \n" +
                "\t\n" +
                "WHERE c.user_id = ?";
        int userAttractionsRemaining = 0;

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
            if (rowSet.next()) {
                userAttractionsRemaining = rowSet.getInt(1);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        finally {
            return userAttractionsRemaining == 0;
        }

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
        if(hasVisitedEveryAttraction(userId)){
                insertUserBadge(userId, DEFENDER_BADGE);
        }

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
