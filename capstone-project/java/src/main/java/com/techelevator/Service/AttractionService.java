package com.techelevator.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AttractionService {
    private static final String GOOGLE_MAPS_API_BASE = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBqXUJKJ-biBNEFS4eDEVxPM-elng6ewqw&q=";
    private static final String API_BASE = "http://localhost:9000";
    private RestTemplate GoogleMapsTemplate;

    public AttractionService(){
        GoogleMapsTemplate = new RestTemplate();


    }

}
