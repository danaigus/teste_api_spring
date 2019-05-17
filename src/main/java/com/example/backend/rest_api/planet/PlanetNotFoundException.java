package com.example.backend.rest_api.planet;

public class PlanetNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1883499148881958096L;

    PlanetNotFoundException(String id) {
        super("Could not find planet " + id);
    }
}