package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Attraction;
import com.techelevator.model.Badge;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcAttractionDao implements AttractionDao{


    private final JdbcTemplate jdbcTemplate;

    public JdbcAttractionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Attraction getAttractionById(int id) {
        Attraction attraction = null;
        String sql = "SELECT attraction_id, name, description, hours_of_operation, address, images, social_media, type_id, latitude, longitude" +
                " FROM attraction WHERE attraction_id = ?";
              
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                attraction = mapRowToAttraction(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return attraction;
    }

    @Override
    public List<Attraction> getAttractions() {
        List<Attraction> attractions = new ArrayList<>();
        String sql = "SELECT attraction_id, name, description, hours_of_operation, address, images, social_media, type_id, latitude, longitude " +
                " FROM attraction ORDER BY name ASC";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Attraction attraction = mapRowToAttraction(results);
                attractions.add(attraction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return attractions;
    }

    @Override
    public List<Attraction> getAttractionByName(String name) {

        if (name == null) throw new IllegalArgumentException("name cannot be null");
        List<Attraction> attractions = new ArrayList<>();
        String sql = "SELECT attraction_id, name, description, hours_of_operation, address, images, social_media, type_id, latitude, longitude " +
                " FROM attraction WHERE name ILIKE ?";
        try {
            String wildcardName = "%" + name + "%";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, wildcardName);
            while (rowSet.next()) {
                Attraction attraction = mapRowToAttraction(rowSet);
                attractions.add(attraction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return attractions;
    }
    @Override
    public List<Attraction> getAttractionByAddress(String address) {
        if (address == null) throw new IllegalArgumentException("address cannot be null");
        List<Attraction> attractions = new ArrayList<>();
        String sql = "SELECT attraction_id, name, description, hours_of_operation, address, images, social_media, type_id , latitude, longitude" +
                " FROM attraction WHERE address ILIKE ?";
        try {
            String wildcardAddress = "%" + address + "%";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, wildcardAddress);
            while (rowSet.next()) {
                Attraction attraction = mapRowToAttraction(rowSet);
                attractions.add(attraction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return attractions;
    }
    @Override
    public List<Attraction> getAttractionByType(String typeName){
        List<Attraction> attractions = new ArrayList<>();
        String sql = "SELECT attraction_id, a.name, description, hours_of_operation, address, images, social_media, type_id , latitude, longitude " +
                " FROM attraction a INNER JOIN type t ON t.id = a.type_id WHERE t.name = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, typeName);
            while (results.next()) {
                Attraction attraction = mapRowToAttraction(results);
                attractions.add(attraction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return attractions;
    }

    @Override
    public BigDecimal getLatitude(int id) {
        BigDecimal latitude = null;
        String sql = "SELECT latitude FROM attraction WHERE attraction_id = ?";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()) {
           // latitude = mapRowToAttraction(result);
            latitude = result.getBigDecimal("latitude");
        }

        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return latitude;
    }

    @Override
    public BigDecimal getLongitude(int id) {
        BigDecimal longitude = null;
        String sql = "SELECT longitude FROM attraction WHERE attraction_id = ? ";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if(result.next()) {
                longitude = result.getBigDecimal("longitude");
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return longitude;
    }
    @Override
    public Attraction updateAttraction(Attraction attraction) {
        Attraction updatedAttraction = null;
        String sql = "UPDATE attraction SET name=?, description=?, hours_of_operation=?, address=?, images=?, social_media=?, type_id=?, latitude=?, longitude=?\n" +
                "\tWHERE attraction_id=?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getHoursOfOperation(), attraction.getAddress(),attraction.getImage(),attraction.getSocialMedia(),attraction.getTypeId(),attraction.getLatitude(), attraction.getLongitude(), attraction.getId());

            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedAttraction = getAttractionById(attraction.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedAttraction;
    }
    @Override
    public int deleteAttractionById(int id){
        int numberOfRows = 0;
        String badgeDeleteSql = "DELETE FROM attraction WHERE attraction_id = ?";
        try {
            numberOfRows = jdbcTemplate.update(badgeDeleteSql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;

    }
    
    @Override
    public Attraction createAttraction(Attraction attraction) {
        Attraction newAttraction = null;
        String insertAttractionSql = "INSERT INTO attraction ( " +
                " name, description, hours_of_operation, address, images, social_media, type_id, latitude, longitude) " +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);" +
                " RETURNING attraction_id";

        try {
            int newAttractionId = jdbcTemplate.queryForObject(insertAttractionSql, int.class, attraction.getName(), attraction.getDescription(),
                    attraction.getHoursOfOperation(), attraction.getAddress(),attraction.getImage(),attraction.getSocialMedia(),attraction.getTypeId(), attraction.getLatitude(), attraction.getLongitude());
            newAttraction = getAttractionById(newAttractionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newAttraction;
    }

    private Attraction mapRowToAttraction(SqlRowSet rs) {
        Attraction attraction = new Attraction();
        attraction.setId(rs.getInt("attraction_id"));
        attraction.setName(rs.getString("name"));
        attraction.setDescription(rs.getString("description"));
        attraction.setHoursOfOperation(rs.getString("hours_of_operation"));
        attraction.setAddress(rs.getString("address"));
        attraction.setImage(rs.getString("images"));
        attraction.setSocialMedia(rs.getString("social_media"));
        attraction.setTypeId(rs.getInt("type_id"));
        attraction.setLatitude(rs.getBigDecimal("latitude"));
        attraction.setLongitude(rs.getBigDecimal("longitude"));
        return attraction;
    }
}
