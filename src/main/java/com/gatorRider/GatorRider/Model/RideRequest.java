package com.gatorRider.GatorRider.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Date;

@Setter
@Getter
@ToString
public class RideRequest {
    private Date date;
    private Time time;
    private String destination;
    private String modelName;
    private int modelYear;
    private int numSeatAvailable;
    private String rideIntro;
    private String driverId;
    private String rideId;
}
