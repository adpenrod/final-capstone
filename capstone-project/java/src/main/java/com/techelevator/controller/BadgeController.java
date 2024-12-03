package com.techelevator.controller;

import com.techelevator.dao.BadgeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Badge;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/badges/")
public class BadgeController {

    private BadgeDao badgeDao;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Badge> listBadges() {

        return badgeDao.getBadge();
    }


    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Badge getBadgeByBadgeId(@PathVariable int id) {
        Badge badge = badgeDao.getBadgeById(id);
        if (badge == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
        } else {
            return badge;
        }
    }


    @RequestMapping(path = "{name}", method = RequestMethod.GET)
    public Badge getBadgeByName(@PathVariable String name) {

        if (badgeDao.getBadgeByName(name) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT A BADGE!");
        } else {
            return badgeDao.getBadgeByName(name);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Badge update(@Valid @RequestBody Badge badge, @PathVariable int id) {
        badge.setId(id);
        try {
            Badge updatedBadge = badgeDao.updateBadge(badge);
            return updatedBadge;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge Not Found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Badge createBadge(@Valid @RequestBody Badge badge) {
        return badgeDao.createBadge(badge);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id, Principal principal) {
        auditLog("delete", id, principal.getName());
        badgeDao.deleteBadgeById(id);
    }

    private void auditLog(String operation, int badge, String username) {
        System.out.println(
                "User: " + username + " performed the operation: " + operation +
                        " on badge: " + badge);
    }

}

