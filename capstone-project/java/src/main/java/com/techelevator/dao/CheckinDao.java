package com.techelevator.dao;


import com.techelevator.model.Checkin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckinDao {

    List<Checkin> getAllCheckins();
    Checkin getCheckinByCheckinId(int checkinId);
    List<Checkin> getCheckinsByUserId(int userId);
    List<Checkin> getCheckinsByAttractionId(int attractionId);
    List<Checkin> getCheckinsByUserName(String userName);
    int getUserIdByCheckin(int userId);
    Checkin createCheckin(Checkin newCheckin);
    int deleteCheckinByCheckinId(int checkinId);

}
