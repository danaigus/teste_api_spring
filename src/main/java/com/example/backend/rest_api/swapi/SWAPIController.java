package com.example.backend.rest_api.swapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/swapi/planets")
public class SWAPIController {

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSWAPIPlanets(@RequestParam(value = "page", required = false) String page) {
        String uri = "https://swapi.co/api/planets";
        if (page != null) {
            uri = uri.concat("/?page=" + page);
        }

        HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(uri,
                HttpMethod.GET,	entity, String.class);

        return response.getBody();
    }
}