package com.example.backend.rest_api.planet;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlanetRepository extends MongoRepository<Planet, String> {

	@Query(value = "{'name': {$regex: ?0, $options: 'i'}}", sort = "{ 'name' : 1 }")
	public List<Planet> findByNameRegex(String name);

	public List<Planet> findAllByOrderByNameAsc();
}