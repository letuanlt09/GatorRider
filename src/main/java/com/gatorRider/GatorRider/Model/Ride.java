package com.gatorRider.GatorRider.Model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
public class Ride {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Date date;

    @NonNull
    private Time time;

    @NonNull
    private String destination;

    @NonNull
    private int numSeatAvailable;

    @NonNull
    private int modelYear;

    @NonNull
    private String modelName;

    @NonNull
    private String rideIntro;

    @NonNull
    @ManyToOne
    private Users driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public Time getTime() {
        return time;
    }

    public void setTime(@NonNull Time time) {
        this.time = time;
    }

    @NonNull
    public String getDestination() {
        return destination;
    }

    public void setDestination(@NonNull String destination) {
        this.destination = destination;
    }

    public int getNumSeatAvailable() {
        return numSeatAvailable;
    }

    public void setNumSeatAvailable(int numSeatAvailable) {
        this.numSeatAvailable = numSeatAvailable;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    @NonNull
    public String getModelName() {
        return modelName;
    }

    public void setModelName(@NonNull String modelName) {
        this.modelName = modelName;
    }

    @NonNull
    public String getRideIntro() {
        return rideIntro;
    }

    public void setRideIntro(@NonNull String riderIntro) {
        this.rideIntro = riderIntro;
    }

    @NonNull
    public Users getDriver() {
        return driver;
    }

    public void setDriver(@NonNull Users driver) {
        this.driver = driver;
    }
}
