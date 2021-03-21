package com.happy_days.travelAgency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tripId;
    private String transportation;
    private String departureMonth;
    private Date departureDate;
    private String hotel;
    private int numberNights;
    private double tripPrice;
    private String tripCurrency;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    @JsonIgnoreProperties("trips")
    private City cityTrip;

    public Trip() {
    }
    public Trip(String transportation, String departureMonth, Date departureDate, String hotel,
                int numberNights, double tripPrice, String tripCurrency,City cityTrip) {
        this.transportation = transportation;
        this.departureMonth = departureMonth;
        this.departureDate = departureDate;
        this.hotel = hotel;
        this.numberNights = numberNights;
        this.tripPrice = tripPrice;
        this.tripCurrency = tripCurrency;
        this.cityTrip = cityTrip;
    }
    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {this.transportation = transportation;}

    public String getDepartureMonth() {
        return departureMonth;
    }

    public void setDepartureMonth(String departureMonth) {
        this.departureMonth = departureMonth;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getHotel() { return hotel; }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getNumberNights() {
        return numberNights;
    }

    public void setNumberNights(int numberNights) {
        this.numberNights = numberNights;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) { this.tripPrice = tripPrice; }

    public City getCityTrip() { return cityTrip; }

    public void setCityTrip(City cityTrip) { this.cityTrip = cityTrip; }

    public String getTripCurrency() { return tripCurrency; }

    public void setTripCurrency(String tripCurrency) { this.tripCurrency = tripCurrency; }
}
