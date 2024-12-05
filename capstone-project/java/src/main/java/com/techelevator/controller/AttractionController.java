package com.techelevator.controller;

import com.techelevator.Service.AttractionService;
import com.techelevator.dao.AttractionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Attraction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.Attr;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/attractions/")
public class AttractionController {
    private AttractionService as;

    private AttractionDao attractionDao;

    public AttractionController(AttractionService as, AttractionDao attractionDao) {
        this.attractionDao = attractionDao;
        this.as = as;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Attraction> listAttractions(@RequestParam(defaultValue = "") String userInput) {
        if (userInput == "bar" || userInput == "bars") {
            return attractionDao.getAttractionByType(userInput);
        } else if (userInput == "restaurant" || userInput == "restaurants") {
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


    /*@RequestMapping(path = "={userInput}", method = RequestMethod.GET)
    public List<Attraction> getAttractionByNameAndAddress(@PathVariable String userInput) {

        if((attractionDao.getAttractionByName(userInput) == null)
                && (attractionDao.getAttractionByAddress(userInput) == null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found." );
        } else if (attractionDao.getAttractionByName(userInput) == null) {

            return attractionDao.getAttractionByAddress(userInput);
        }else {
            return attractionDao.getAttractionByName(userInput);
        }
    }*/

    @RequestMapping(path = "name/{userInput}", method = RequestMethod.GET)
    public List<Attraction> getAttractionByName(@PathVariable String userInput) {

        if ((attractionDao.getAttractionByName(userInput) == null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
        } else {
            return attractionDao.getAttractionByName(userInput);
        }
    }

    @RequestMapping(path = "address/{userInput}", method = RequestMethod.GET)
    public List<Attraction> getAttractionByAddress(@PathVariable String userInput) {

        if ((attractionDao.getAttractionByAddress(userInput) == null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
        } else {
            return attractionDao.getAttractionByAddress(userInput);
        }
    }
        @RequestMapping(path = "latitude/{id}", method = RequestMethod.GET )
        public BigDecimal getLatitude(@PathVariable int id){

            if(attractionDao.getLatitude(id) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
            }else {
                return attractionDao.getLatitude(id);
            }
        }
    @RequestMapping(path = "longitude/{id}", method = RequestMethod.GET )
    public BigDecimal getLongitude(@PathVariable int id){

        if(attractionDao.getLongitude(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
        }else {
            return attractionDao.getLongitude(id);
        }
    }




    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
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

