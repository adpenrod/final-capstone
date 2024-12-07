package com.techelevator.controller;

import com.techelevator.Service.AttractionService;
import com.techelevator.dao.TypeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Type;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/types/")
public class TypeController {

    private AttractionService as;
    private TypeDao typeDao;

    public TypeController(AttractionService as, TypeDao typeDao) {
        this.typeDao = typeDao;
        this.as = as;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Type> getTypes() {
        return typeDao.getTypes();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Type getTypeById(@PathVariable int id) {
        Type attractionType = typeDao.getTypeById(id);
        if (attractionType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "We cannot find this type of attraction.");
        } else {
            return attractionType;
        }
    }

    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public Type getTypeByName(@PathVariable String name) {

        if ((typeDao.getTypeByName(name) == null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "We cannot find this type of attraction.");
        } else {
            return typeDao.getTypeByName(name);
        }
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public Type update (@Valid @RequestBody Type type,@PathVariable int id){
        type.setId(id);
        try {
            Type updatedType = typeDao.updateType(type);
            return updatedType;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type of Attraction not found.");
        }
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Type createType (@Valid @RequestBody Type type){
        return typeDao.createType(type);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete ( @PathVariable int id, Principal principal){
        auditLog("delete", id, principal.getName());
        typeDao.deleteTypeById(id);
    }

    private void auditLog (String operation,int type, String username){
        System.out.println(
                "User: " + username + " performed the operation: " + operation +
                        " on attraction type: " + type);
    }


}


