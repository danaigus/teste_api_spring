package com.example.backend.rest_api.planet;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    @Autowired
    private PlanetRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planet createPlanet(@Valid @RequestBody Planet planet) {
        int appears = 0;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange("https://swapi.co/api/planets/?search="+planet.getName(),
                HttpMethod.GET,	entity, String.class);

		JsonObject jsonObject = new JsonParser().parse(response.getBody()).getAsJsonObject();
        if (jsonObject.get("count").getAsInt() != 0) {
            JsonArray array = jsonObject.getAsJsonArray("results").get(0)
				.getAsJsonObject()
                .getAsJsonArray("films");
            appears = array.size();
        }
        planet.setAppears(appears);
        
        return repository.save(planet);
    }

    @GetMapping
    public List<Planet> getAllPlanets(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return repository.findByNameRegex(name);
        } else {
            return repository.findAllByOrderByNameAsc();
        }
    }

    @GetMapping(value = "/{id}")
    public Planet getPlanetById(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new PlanetNotFoundException(id));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Planet updatePlanet(@PathVariable("id") String id, @Valid @RequestBody Planet planet) {
        return repository.findById(id)
                .map(dbPlanet -> {
                    planet.setId(id);
                    return repository.save(planet);
                })
                .orElseThrow(() -> new PlanetNotFoundException(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlanet(@PathVariable("id") String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PlanetNotFoundException(id);
        }
    }
}