package com.techelevator.controller;

import com.techelevator.Service.AttractionService;
import com.techelevator.dao.CheckinDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Checkin;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/checkins/")
public class CheckinController {
    private AttractionService as;
    private CheckinDao checkinDao;

    public CheckinController(AttractionService as, CheckinDao checkinDao) {
        this.checkinDao = checkinDao;
        this.as = as;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "all", method = RequestMethod.GET)
    public List<Checkin> allUserCheckins() {
        return checkinDao.getAllCheckins();
    }
    @RequestMapping(path = "attraction/{attractionId}", method = RequestMethod.GET)
    public List<Checkin> getCheckinsByAttractionId(@PathVariable int attractionId ) {
        return checkinDao.getCheckinsByAttractionId(attractionId);
    }

    /*
    @RequestMapping(method = RequestMethod.GET)
    public List<Checkin> getCheckinHistory(@RequestParam(value = "user_name", defaultValue = "") String userName,
                                           @RequestParam(value = "user_id", defaultValue = "0") int userId) {

        if ((checkinDao.getCheckinsByUserName(userName) == null) || (checkinDao.getCheckinsByUserId(userId) == null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Checkin history.");
        }
        try {
            if (!userName.isEmpty()) {
                return checkinDao.getCheckinsByUserName(userName);
            } else if (userId != 0) {
                return checkinDao.getCheckinsByUserId(userId);
            } else {
                return checkinDao.getCheckinsByUserName(userName);
            }
        }
    }*/
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Checkin getCheckinByCheckinId(@PathVariable int id) {
        Checkin checkin = checkinDao.getCheckinByCheckinId(id);
        if (checkin == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You haven't been here yet! Get exploring!");
        } else {
            return checkin;
        }
    }

    @RequestMapping(path = "user/{id}", method = RequestMethod.GET)
    public List<Checkin> getCheckinByUserId(@PathVariable int id) {
        List<Checkin> checkin = checkinDao.getCheckinsByUserId(id);
        if (checkin == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You haven't been anywhere yet! Get exploring!");
        } else {
            return checkin;
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Checkin createCheckin(@Valid @RequestBody Checkin newCheckin) {
        return checkinDao.createCheckin(newCheckin);

        //TODO: Don't we need to call award badges to see if this checkin results in a bdage?
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id, Principal principal) {
        auditLog("delete", id, principal.getName());
        checkinDao.deleteCheckinByCheckinId(id);
    }

    private void auditLog(String operation, int checkin, String username) {
        System.out.println(
                "User: " + username + " performed the operation: " + operation +
                        " on checkin: " + checkin);
    }
}