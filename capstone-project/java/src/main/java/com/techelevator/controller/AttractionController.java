package com.techelevator.controller;

import com.techelevator.dao.AttractionDao;
import com.techelevator.model.Attraction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AttractionController {

    private AttractionDao attractionDao;

    @RequestMapping(path= "/attractions", method= RequestMethod.GET)
    public List<Attraction> listAttractions(@RequestParam(defaultValue = "")String title_like){
        if(title_like.equals("")){
           return attractionDao.getAttractions();
        }
        if(!title_like.equals("")){
           // return attractionDao.getAttractionByName(title_like);
        }
    }
}
