package com.happy_days.travelAgency.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long countryId;

    private String countryName;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<City> cities = new HashSet<City>();

    public Country() {
    }

    public Country(String countryName, Set<City> cities) {
        this.countryName = countryName;
        this.cities = cities;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
