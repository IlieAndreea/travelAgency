package com.happy_days.travelAgency.controller;

import com.happy_days.travelAgency.exception.ResourceNotFoundException;
import com.happy_days.travelAgency.model.City;
import com.happy_days.travelAgency.model.Trip;
import com.happy_days.travelAgency.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityId)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for the requested id: " + cityId));
        return ResponseEntity.ok().body(city);
    }

    @PostMapping("/cities")
    public City createCity(@Valid @RequestBody City city) {
        return cityRepository.save(city);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> updateCity(@PathVariable(value = "id") Long cityId,
                                           @Valid @RequestBody City cityDetails) throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for the requested id: " + cityId));
        city.setCityName(cityDetails.getCityName());
        city.setCountry(cityDetails.getCountry());
        city.setTrips(cityDetails.getTrips());
        final City updatedCity = cityRepository.save(city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/cities/{id}")
    public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for the requested id: "+ cityId));
        cityRepository.delete(city);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
