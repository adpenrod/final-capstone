package com.techelevator.Service;

import org.springframework.web.client.RestTemplate;

public class AttractionService {
    private static final String GOOGLE_MAPS_API_BASE = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBqXUJKJ-biBNEFS4eDEVxPM-elng6ewqw&q=";
    private RestTemplate GoogleMapsTemplate;

    public AttractionService(){
        GoogleMapsTemplate = new RestTemplate();


    }

}
