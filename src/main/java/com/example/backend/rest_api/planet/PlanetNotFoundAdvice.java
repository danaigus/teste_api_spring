package com.example.backend.rest_api.planet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlanetNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(PlanetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String planetNotFoundHandler(PlanetNotFoundException ex) {
        return ex.getMessage();
    }
}