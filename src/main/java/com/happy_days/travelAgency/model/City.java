package com.happy_days.travelAgency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cityId;

    private String cityName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    @JsonIgnoreProperties("cities")
    private Country country;


    @OneToMany(mappedBy = "cityTrip", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Trip> trips = new HashSet<Trip>();

    public City() {
    }

    public City(String cityName, Country country, Set<Trip> trips) {
        this.cityName = cityName;
        this.country = country;
        this.trips = trips;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
}




