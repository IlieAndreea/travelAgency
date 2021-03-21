package com.happy_days.travelAgency.controller;

import com.happy_days.travelAgency.exception.ResourceNotFoundException;
import com.happy_days.travelAgency.model.Trip;
import com.happy_days.travelAgency.repository.TripRepository;
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
public class TripController {
    @Autowired
    private TripRepository tripRepository;
    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
    @GetMapping("/trips/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable(value = "id") Long tripId)
            throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for the requested id: " + tripId));
        return ResponseEntity.ok().body(trip); }
    @PostMapping("/trips")
    public Trip createTrip(@Valid @RequestBody Trip trip) {
        return tripRepository.save(trip);
    }
    @PutMapping("/trips/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable(value = "id") Long tripId,
                                           @Valid @RequestBody Trip tripDetails) throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for the requested id: " + tripId));
        trip.setTransportation(tripDetails.getTransportation());
        trip.setDepartureMonth(tripDetails.getDepartureMonth());
        trip.setDepartureDate(tripDetails.getDepartureDate());
        trip.setHotel(tripDetails.getHotel());
        trip.setNumberNights(tripDetails.getNumberNights());
        trip.setTripPrice(tripDetails.getTripPrice());
        trip.setTripCurrency(tripDetails.getTripCurrency());
        trip.setCityTrip(tripDetails.getCityTrip());
        final Trip updatedTrip = tripRepository.save(trip);
        return ResponseEntity.ok(updatedTrip); }
    @DeleteMapping("/trips/{id}")
    public Map<String, Boolean> deleteTrip(@PathVariable(value = "id") Long tripId)
            throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for the requested id: "+ tripId));
        tripRepository.delete(trip);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response; }
}
