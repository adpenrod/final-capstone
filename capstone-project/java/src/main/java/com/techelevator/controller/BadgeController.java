package com.techelevator.controller;

import com.techelevator.Service.AttractionService;
import com.techelevator.dao.BadgeDao;
import com.techelevator.dao.UserBadgeDao;
import com.techelevator.dao.UserDao;
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

    private UserBadgeDao userBadgeDao;
    private UserDao userDao;
    private AttractionService as;

    public BadgeController(BadgeDao badgeDao, AttractionService as, UserDao userDao) {
        this.badgeDao = badgeDao;
        this.userDao = userDao;
        this.as = as;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public List<Badge> listBadges( Principal principal) {

        return badgeDao.getUserBadges( userDao.getUserByUsername(principal.getName()).getId() );
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Badge getBadgeByBadgeId(@PathVariable int id) {
        Badge badge = badgeDao.getBadgeById(id);
        if (badge == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge not found.");
        } else {
            return badge;
        }
    }

    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public Badge getBadgeByName(@PathVariable String name) {

        if (badgeDao.getBadgeByName(name) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT A BADGE!");
        } else {
            return badgeDao.getBadgeByName(name);
        }
    }

    @RequestMapping(path = "type/{typeId}", method = RequestMethod.GET)
    public Badge getBadgeByTypeId(@PathVariable int typeId) {
        Badge badge = badgeDao.getBadgeByTypeId(typeId);
        if (badge == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge not found.");
        } else {
            return badge;
        }
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Badge update( @RequestBody Badge badge, @PathVariable int id) {
        badge.setId(id);
        try {
            Badge updatedBadge = badgeDao.updateBadge(badge);
            return updatedBadge;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge Not Found");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Badge createBadge(@Valid @RequestBody Badge badge) {
        return badgeDao.createBadge(badge);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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