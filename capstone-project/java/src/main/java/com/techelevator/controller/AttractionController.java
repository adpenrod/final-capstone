package com.techelevator.controller;

import com.techelevator.dao.AttractionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Attraction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/attractions/")
public class AttractionController {

    private AttractionDao attractionDao;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Attraction> listAttractions(@RequestParam(defaultValue = "") String userInput) {
        if(userInput == "bar" || userInput == "bars"){
            return attractionDao.getAttractionByType(userInput);
        }else if(userInput == "restaurant" || userInput == "restaurants"){
            return attractionDao.getAttractionByType(userInput);
        }
        return attractionDao.getAttractions();
    }


    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Attraction getAttractionByAttractionId(@PathVariable int id) {
        Attraction attraction = attractionDao.getAttractionById(id);
        if (attraction == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
        } else {
            return attraction;
        }
    }


    @RequestMapping(path = "{name}", method = RequestMethod.GET)
    public Attraction getAttractionByNameAndAddress(@PathVariable String userInput) {

        if((attractionDao.getAttractionByName(userInput) == null)
                && (attractionDao.getAttractionByAddress(userInput) == null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found." );
        } else if (attractionDao.getAttractionByName(userInput) == null) {

            return attractionDao.getAttractionByAddress(userInput);
        }else {
            return attractionDao.getAttractionByName(userInput);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Attraction update(@Valid @RequestBody Attraction attraction, @PathVariable int id) {
        attraction.setId(id);
        try {
            Attraction updatedAttraction = attractionDao.updateAttraction(attraction);
            return updatedAttraction;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction Not Found");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Attraction createAttraction(@Valid @RequestBody Attraction attraction) {
        return attractionDao.createAttraction(attraction);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id, Principal principal) {
        auditLog("delete", id, principal.getName());
        attractionDao.deleteAttractionById(id);
    }

    private void auditLog(String operation, int attraction, String username) {
        System.out.println(
                "User: " + username + " performed the operation: " + operation +
                        " on attraction: " + attraction);
    }

}

