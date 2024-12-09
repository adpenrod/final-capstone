package com.techelevator.controller;

import com.techelevator.Service.AttractionService;
import com.techelevator.dao.UserBadgeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.UserBadgeDto;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/ubadges/")
public class UserBadgeController {
    private UserBadgeDao userbadgedao;
    private AttractionService as;

    public UserBadgeController(UserBadgeDao userbadgedao) {
        this.userbadgedao = userbadgedao;
        this.as = as;
    }

    @RequestMapping(path = "visits/{userId}/{attractionType}", method= RequestMethod.GET)
    public ResponseEntity<String> hasVisitedAttractionTypeFiveTimes(@PathVariable int userId, @PathVariable String attractionType){

        try{
            boolean hasVisited = userbadgedao.hasVistedAttractionTypeFiveTimes(userId, attractionType);
            if(hasVisited) {
                return ResponseEntity.ok("User has visited the attraction 5 times.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User has not visited the attraction 5 times.");
            }
        } catch(DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Error checking visit: " + e.getMessage());
        }

    }

    @RequestMapping(path = "id/{name}", method= RequestMethod.GET)
    public ResponseEntity<String> getBadgeIdByName(@PathVariable String name) {


        try {
            int badgeId = userbadgedao.getBadgeIdByName(name);
            if(badgeId != -1){
                return ResponseEntity.ok("Badge ID: " + badgeId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND) .body("Badge not found with name: " + name);

            }

        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Error fetching badge ID: " + e.getMessage());
        }
    }



//    @RequestMapping(path = "award", method=RequestMethod.POST)
//    public ResponseEntity<String> awardBadge(@Valid @RequestBody int userId, String attractionType, String badgeName){
//        try{
//            userbadgedao.checkAndAwardBadge(userId,attractionType,badgeName);
//            return ResponseEntity.ok("Badge awarded successfully.");
//        } catch (DaoException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Error awarding badge: " + e.getMessage());
//        }
//    }

    @RequestMapping(path = "award", method=RequestMethod.POST)
    public ResponseEntity<String> awardBadge(@Valid @RequestBody UserBadgeDto request){
        try{
            boolean hasVisitedEnough = userbadgedao.hasVistedAttractionTypeFiveTimes(request.getUserId(), request.getAttractionType());
            boolean hasVisitedEverything = userbadgedao.hasVisitedEveryAttraction(request.getUserId());

            if(!hasVisitedEnough && !hasVisitedEverything){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST) .body("You have not visited enough of attractions of type " + request.getAttractionType() + " to get this badge");
            } else{
                userbadgedao.checkAndAwardBadge(request.getUserId(), request.getAttractionType(), request.getBadgeName());
                return ResponseEntity.ok("Badge awarded successfully.");
            }

        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Error awarding badge: " + e.getMessage());
        }
    }
}
