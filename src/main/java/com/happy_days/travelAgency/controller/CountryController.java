package com.happy_days.travelAgency.controller;

import com.happy_days.travelAgency.exception.ResourceNotFoundException;
import com.happy_days.travelAgency.model.Country;
import com.happy_days.travelAgency.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") Long countryId)
            throws ResourceNotFoundException {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found for the requested id: " + countryId));
        return ResponseEntity.ok().body(country);
    }

    @PostMapping("/countries")
    public Country createCountry(@Valid @RequestBody Country country) {
        return countryRepository.save(country);
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") Long countryId,
                                                 @Valid @RequestBody Country countryDetails) throws ResourceNotFoundException {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found for the requested id: " + countryId));
        country.setCountryName(countryDetails.getCountryName());
        country.setCities(countryDetails.getCities());
        final Country updatedCountry = countryRepository.save(country);
        return ResponseEntity.ok(updatedCountry);
    }

    @DeleteMapping("/countries/{id}")
    public Map<String, Boolean> deleteCountry(@PathVariable(value = "id") Long countryId)
            throws ResourceNotFoundException {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found for the requested id: "+ countryId));
        countryRepository.delete(country);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
