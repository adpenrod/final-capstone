package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Checkin;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCheckinDao implements CheckinDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcCheckinDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Checkin getCheckinByCheckinId(int checkinId) {
        Checkin checkin = null;
        String sql = "SELECT checkin_id, user_id, attraction_id, checkin_time, notes " +
                " FROM checkin WHERE checkin_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, checkinId);
            if (results.next()) {
                checkin = mapRowToCheckin(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return checkin;
    }
    @Override
    public List<Checkin> getAllCheckins() {
        List<Checkin> checkins = new ArrayList<>();
        String sql = "SELECT checkin_id, user_id, attraction_id, checkin_time, notes " +
                " FROM checkin ORDER BY checkin_time DESC";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Checkin attraction = mapRowToCheckin(results);
                checkins.add(attraction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return checkins;
    }
    @Override
    public List<Checkin> getCheckinsByUserId(int userId) {
        List<Checkin> checkins = new ArrayList<>();
        String sql = "SELECT checkin_id, user_id, attraction_id, checkin_time, notes " +
                " FROM checkin WHERE user_id = ?";
        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
            while (rowSet.next()) {
                Checkin checkin = mapRowToCheckin(rowSet);
                checkins.add(checkin);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return checkins;
    }
    @Override
    public List<Checkin> getCheckinsByAttractionId(int attractionId) {

        List<Checkin> checkins = new ArrayList<>();
        String sql = "SELECT checkin_id, user_id, attraction_id, checkin_time, notes " +
                " FROM checkin WHERE attraction_id = ?";
        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, attractionId);
            while (rowSet.next()) {
                Checkin checkin = mapRowToCheckin(rowSet);
                checkins.add(checkin);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return checkins;
    }
    @Override
    public List<Checkin> getCheckinsByUserName(String userName) {
        List<Checkin> checkins = new ArrayList<>();
        String sql = "SELECT checkin_id, c.user_id, attraction_id, checkin_time, notes " +
                " FROM checkin c INNER JOIN users u ON c.user_id = u.user_id WHERE u.username = ?";
        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userName);
            while (rowSet.next()) {
                Checkin checkin = mapRowToCheckin(rowSet);
                checkins.add(checkin);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return checkins;
    }

    @Override
    public int getUserIdByCheckin(int id){
        int userId = 0;
        String sql = "SELECT user_id" +
                " FROM checkin WHERE checkin_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                userId = results.getInt("user_id");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return userId;
    }
    @Override
    public int deleteCheckinByCheckinId(int checkinId) {
        int numberOfRows = 0;
        String checkinDeleteSql = "DELETE FROM checkin WHERE checkin_id = ?";
        try {
            numberOfRows = jdbcTemplate.update(checkinDeleteSql, checkinId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }
    @Override
    public Checkin createCheckin(Checkin checkin) {
        Checkin newCheckin = null;
        String insertCheckinSql = "INSERT INTO checkin ( " +
                " user_id, attraction_id, notes) " +
                " VALUES (?, ?, ?)" +
                " RETURNING checkin_id";
        try {
            int newCheckinId = jdbcTemplate.queryForObject(insertCheckinSql, int.class, checkin.getUserId(),
                    checkin.getAttractionId(), checkin.getNotes());
            newCheckin = getCheckinByCheckinId(newCheckinId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCheckin;
    }
    private Checkin mapRowToCheckin(SqlRowSet rs) {
        Checkin checkin = new Checkin();
        checkin.setCheckinId(rs.getInt("checkin_id"));
        checkin.setUserId(rs.getInt("user_id"));
        checkin.setAttractionId(rs.getInt("attraction_id"));
        checkin.setCheckinTime(rs.getTimestamp("checkin_time").toLocalDateTime());
        checkin.setNotes(rs.getString("notes"));

        return checkin;
    }

}
